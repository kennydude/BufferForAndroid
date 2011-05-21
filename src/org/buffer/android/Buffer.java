package org.buffer.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.util.Log;

public class Buffer extends Activity {

	private static final String TAG = "Buffer";
	TextView display;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        display = (TextView) this.findViewById(R.id.display);
        UpdateContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.add:
    		Intent i = new Intent(this, Add.class);
    		startActivity(i);
    		break;
    	case R.id.quit:
    		finish();
    		break;
    	}
    	return false;
    }

	public void UpdateContent() {
		try {
			ProcessResponse(SearchRequest("android"));
		} catch(Exception e) {
			Log.v(TAG, "Exception: " + e.getMessage());
		}
	}

	public String SearchRequest(String searchString) throws MalformedURLException, IOException {
		return "existing buffered tweets will come here";
	}

	public void ProcessResponse(String resp) throws IllegalStateException, IOException, JSONException, NoSuchAlgorithmException {
		display.setText(resp);
	}
}