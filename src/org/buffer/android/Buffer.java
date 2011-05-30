package org.buffer.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Buffer extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Click listeners
		View addButton = this.findViewById(R.id.add_button);
		addButton.setOnClickListener(this);
		View quitButton = this.findViewById(R.id.quit_button);
		quitButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_button:
			Intent i = new Intent(this, Add.class);
			startActivity(i);
			break;
		case R.id.quit_button:
			finish();
			break;
		}
	}
}