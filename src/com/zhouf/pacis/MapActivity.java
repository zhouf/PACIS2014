package com.zhouf.pacis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 用于显示在线的地图
 * 
 * @author zhouf
 * 
 */
public class MapActivity extends Activity{

	private String TAG = "MapActivity";
	private String urlPrefix = "http://pacis2014.org/App/img/";
	private Handler handler;
	private ImageView map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		setTitle(R.string.map_title);

		final TextView mapTitle = (TextView) findViewById(R.id.map_title);
		String getTitle = this.getIntent().getStringExtra("mapTitle");
		mapTitle.setText("Please wait...");
		String mapFileName = this.getIntent().getStringExtra("mapFile");
		Log.i(TAG, "mapFileName:" + mapFileName);

		map = (ImageView) findViewById(R.id.img_map);
		
		byte[] data = getLocalImage(mapFileName);
		if(data==null){
			data = getLocalImage("CP.png");
		}
		if(data!=null){
			Log.i(TAG,"存在图片资源" + mapFileName );
			
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			map.setImageBitmap(bitmap);
			
			mapTitle.setText(getTitle);
		}else{
			Log.i(TAG,"没有找到图片资源" + mapFileName );
			//Toast.makeText(this, "Sorry,The resource not found", Toast.LENGTH_LONG).show();
			finish();
		}
		
		
		

		/*
		 * byte[] data; try { Log.i(TAG,"getImage1"); String url = urlPrefix +
		 * mapFileName; Log.i(TAG, "url:" + url); data = getImage(url);
		 * Log.i(TAG,"getImage2");
		 * 
		 * if(data!=null){ Bitmap bitmap = BitmapFactory.decodeByteArray(data,
		 * 0, data.length); map.setImageBitmap(bitmap); }else{
		 * Log.i(TAG,"data is null"); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.map, menu);
		return false;
	}

	public static byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		if (conn.getResponseCode() == 200) {
			return readStream(inStream);
		}
		return null;
	}

	private byte[] getLocalImage(String mapFileName2) {
		AssetManager am = getAssets();

		Log.i(TAG, "mapFileName2:" + mapFileName2);
		
		InputStream inStream;
		try {
			inStream = am.open(mapFileName2);
			return readStream(inStream);
		} catch (IOException e) {
			Log.e(TAG,"IOException:" + e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

}
