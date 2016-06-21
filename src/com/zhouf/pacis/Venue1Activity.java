package com.zhouf.pacis;

import com.zhouf.util.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class Venue1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue1);
		
		WebView webView = (WebView)findViewById(R.id.venue1WebView);
		webView.loadUrl(Constant.VENUE_URL1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.venue1, menu);
		return false;
	}

}
