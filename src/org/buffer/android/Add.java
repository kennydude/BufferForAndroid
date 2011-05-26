package org.buffer.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Add extends Activity {

	WebView addWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);

		addWebView = (WebView) findViewById(R.id.add_web_view);
		addWebView.getSettings().setJavaScriptEnabled(true);
		addWebView.loadUrl("http://bufferapp.com/add");
	}
}
