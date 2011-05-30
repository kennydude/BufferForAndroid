package org.buffer.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Add extends Activity {

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.add);

		webView = (WebView) findViewById(R.id.add_web_view);
		webView.getSettings().setJavaScriptEnabled(true);

		final Activity activity = this;
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				activity.setProgress(progress * 100);
			}
		});

		Intent intent = getIntent();
		String text = intent.getStringExtra(Intent.EXTRA_TEXT);
		String subject = intent.getStringExtra(Intent.EXTRA_SUBJECT);

		String url = "http://bufferapp.com/add";
		String urlParams = "";

		if (text != null) {
			text = convertTextFromTweetDeck(text);
			urlParams = urlParamsFromTextAndSubject(text, subject);
		}
		webView.loadUrl(url + urlParams);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	public String convertTextFromTweetDeck(String text) {
		if (text.contains("Sent via TweetDeck")) {
			text = text.substring(0, text.indexOf("Original Tweet"));
			text = "RT @" + text;
		}
		return text;
	}

	public String urlParamsFromTextAndSubject(String text, String subject) {
		String urlParams = "";
		if (text.matches("^http://\\S+$")) {
			urlParams = "?url=" + text;
			if (subject != null) {
				urlParams += "&text=" + subject;
			}
		} else {
			urlParams = "?text=" + text;
		}
		return urlParams;
	}
}
