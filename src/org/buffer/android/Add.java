package org.buffer.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;

public class Add extends Activity implements OnClickListener {

	private static final String TAG = "Buffer";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);

		// Click listener
		View addButton = this.findViewById(R.id.add_button);
		addButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.add_button:
			Log.d(TAG, "add button pressed");
			break;
		}
	}
}
