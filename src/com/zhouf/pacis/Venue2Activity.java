package com.zhouf.pacis;

import com.zhouf.util.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class Venue2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue2);
		
		WebView webView = (WebView)findViewById(R.id.venue2WebView);
		webView.loadUrl(Constant.VENUE_URL2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.venue1, menu);
		return false;
	}

}
