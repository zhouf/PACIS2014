package com.zhouf.pacis;

import com.zhouf.util.ConnectorUtil;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;

public class SplashActivity extends TabActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		TabHost mTabHost = getTabHost();

		mTabHost.addTab(mTabHost.newTabSpec("Twitter").setIndicator(
				"Schedule",
				getResources().getDrawable(android.R.drawable.arrow_down_float)).setContent(
				new Intent(this, MainActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("Venue").setIndicator(
				"Venue",
				getResources().getDrawable(android.R.drawable.arrow_down_float)).setContent(
				new Intent(this, VenueActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("Facebook").setIndicator(
				"Contacts",
				getResources().getDrawable(android.R.drawable.arrow_down_float)).setContent(
				new Intent(this, ContactActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("Facebook").setIndicator(
				"Attractions",
				getResources().getDrawable(android.R.drawable.arrow_down_float)).setContent(
				new Intent(this, AttractionActivity.class)));
		mTabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.splash, menu);
		return false;
	}

}
