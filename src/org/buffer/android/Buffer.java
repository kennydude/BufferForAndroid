package org.buffer.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	static String url = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
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
		String newFeed = url + searchString;
		StringBuilder response = new StringBuilder();
		Log.v(TAG, "search url: " + newFeed);
		URL url = new URL(newFeed);

		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		if(httpcon.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader input = new BufferedReader(
					new InputStreamReader(httpcon.getInputStream()),
					8192);
			String strLine = null;
			while ((strLine = input.readLine()) != null) {
				response.append(strLine);
			}
			input.close();
		}
		return response.toString();
	}

	public void ProcessResponse(String resp) throws IllegalStateException, IOException, JSONException, NoSuchAlgorithmException {
		display.setText(resp);
	}
}