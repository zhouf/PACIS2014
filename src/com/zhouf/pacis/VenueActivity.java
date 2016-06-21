package com.zhouf.pacis;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class VenueActivity extends ActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue);

		/*WebView webView1 = (WebView)findViewById(R.id.venueWebView1);
		webView1.loadUrl("http://pacis2014.org/App/web/Venue.htm");
		
		WebView webView2 = (WebView)findViewById(R.id.venueWebView1);
		webView2.loadUrl("http://pacis2014.org/App/web/Traffic.htm");
		
		WebView webView3 = (WebView)findViewById(R.id.venueWebView1);
		webView3.loadUrl("http://pacis2014.org/App/web/Accommondation.htm");
		
		WebView webView4 = (WebView)findViewById(R.id.venueWebView1);
		webView4.loadUrl("http://pacis2014.org/App/web/Map.htm");*/

		TabHost mTabHost = (TabHost) findViewById(R.id.mytabhost);
		mTabHost.setup();
        mTabHost.setup(this.getLocalActivityManager());
        
		TabWidget tabWidget = mTabHost.getTabWidget();

		mTabHost.addTab(mTabHost.newTabSpec("Introduction").setIndicator("Introduction")
				.setContent(new Intent(this, Venue1Activity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("Transportation").setIndicator("Transportation")
				.setContent(new Intent(this, Venue2Activity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("Lodgings").setIndicator("Lodgings")
				.setContent(new Intent(this, Venue3Activity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("Map").setIndicator("Map")
				.setContent(new Intent(this, Venue4Activity.class)));
		mTabHost.setCurrentTab(0);

		int height = 60;
		// int width =45;

		/*for (int i = 0; i < tabWidget.getChildCount(); i++) {

			tabWidget.getChildAt(i).getLayoutParams().height = height;
			final TextView tv = (TextView) tabWidget.getChildAt(i)
					.findViewById(android.R.id.title);
			tv.setTextColor(this.getResources().getColorStateList(
					android.R.color.primary_text_dark));
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.venue, menu);
		return false;
	}

}
