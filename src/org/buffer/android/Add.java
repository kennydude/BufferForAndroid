package org.buffer.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;

public class Add extends Activity implements OnClickListener {

	private static final String TAG = "Buffer";
	EditText tweetTextView;
	Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);

		tweetTextView = (EditText) this.findViewById(R.id.tweet_text);

		// Click listener
		addButton = (Button) this.findViewById(R.id.add_button);
		addButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.add_button:
			Log.d(TAG, tweetTextView.getText().toString());
			this.finish();
			break;
		}
	}
}
