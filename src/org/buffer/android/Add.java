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
		String url = "http://bufferapp.com/add";
		if (text != null) {
			// Handle tweetdeck retweeting
			if (text.contains("Sent via TweetDeck")) {
				text = text.substring(0, text.indexOf("Original Tweet"));
				text = "RT @" + text;
			}

			// If text is entirely a URL we can pass it to Buffer as such
			if (text.matches("^http://\\S+$")) {
				url += "?url=" + text;
				// In this case, it's likely the page title is shared in the subject!
				text = intent.getStringExtra(Intent.EXTRA_SUBJECT);
				if (text != null) {
					url += "&text=" + text;
				}
			}

			// Otherwise, just pass the text we have
			else {
				url += "?text=" + text;
			}
		}
		webView.loadUrl(url);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}
