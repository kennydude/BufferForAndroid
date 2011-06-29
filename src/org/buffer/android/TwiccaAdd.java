package org.buffer.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Allows you to share directly from Twicca's menu! how cool is that! :D
 * @author kennydude
 *
 */
public class TwiccaAdd extends Activity {
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
		String url = "http://bufferapp.com/add?source=android";
		
		String user_name = intent.getStringExtra( "user_screen_name" );
		if(user_name == null)
			user_name = "";
		String tweet_text = intent.getStringExtra( Intent.EXTRA_TEXT );
		if(tweet_text == null)
			tweet_text = "";
		
		String text = "RT @" + user_name + ": " + tweet_text;
		url += "&text=" + Uri.encode(text);
		
		webView.loadUrl(url);
	}
	
}
