package org.buffer.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
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
			text = convertTextFromTwidroyd(text);
			text = convertTextFromTwitter(text);
			text = convertTextFromSeesmic(text);
			urlParams = urlParamsFromTextAndSubject(text, subject);
		}
		if(urlParams != "") urlParams += "&source=android";
		else urlParams += "?source=android";
		webView.loadUrl(url + urlParams);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	public String convertTextFromTweetDeck(String text) {
		if (text.contains("Sent via TweetDeck")) {
			if (text.contains("Original Tweet")) {
				text = text.substring(0, text.indexOf("Original Tweet"));
				text = "RT @" + text;
			}
			if (text.contains("Original Facebook Status")) {
				text = text.substring(0, text.indexOf("Original Facebook Status"));
			}
		}
		return text;
	}

	public String convertTextFromTwidroyd(String text) {
		if (text.contains("--\nshared via Twidroyd")) {
			text = text.substring(0, text.indexOf("--\nshared via Twidroyd"));
			text = text.replaceFirst(":", ": ");
			text = "RT " + text;
		}
		return text;
	}

	public String convertTextFromTwitter(String text) {
		if (text.contains(") has shared a Tweet with you:")) {
			text = text.substring(text.indexOf("\"")+1, text.length());
			text = text.substring(0, text.indexOf("\"\n--http://twitter.com/"));
			text = "RT @" + text;
		}
		return text;
	}

	public String convertTextFromSeesmic(String text) {
		if (text.contains("Sent via Seesmic")) {
			text = text.substring(text.indexOf("(")+1, text.length());
			text = text.replaceFirst("\\):", ": ");
			text = text.substring(0, text.indexOf("\n\nhttp://twitter.com/"));
			text = "RT @" + text;
		}
		return text;
	}

	public String urlParamsFromTextAndSubject(String text, String subject) {
		String urlParams = "";
		if (looksLikeURL(text)) {
			urlParams = "?url=" + Uri.encode(text);
			if (subject != null) {
				urlParams += "&text=" + Uri.encode(subject);
			}
		} else {
			urlParams = "?text=" + Uri.encode(text);
		}
		return urlParams;
	}

	public Boolean looksLikeURL(String text) {
		return text.matches("^https?://\\S+$");
	}
}
