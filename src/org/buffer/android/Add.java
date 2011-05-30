package org.buffer.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Add extends Activity {

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);

		webView = (WebView) findViewById(R.id.add_web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://bufferapp.com/add");
	}
}
