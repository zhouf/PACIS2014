package com.zhouf.pacis;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

public class AttractionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attraction);
		
		WebView webView = (WebView)findViewById(R.id.attractionWebView);
		webView.loadUrl("http://pacis2014.org/App/web/Attractions.htm");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.attraction, menu);
		return false;
	}

}
