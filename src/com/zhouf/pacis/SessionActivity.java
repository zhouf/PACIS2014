package com.zhouf.pacis;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhouf.entity.SessionItem;
import com.zhouf.entity.TaskItem;
import com.zhouf.util.Constant;
import com.zhouf.util.DataUtil;
import com.zhouf.util.GroupListAdapter;
import com.zhouf.util.HttpDownloader;

public class SessionActivity extends Activity {

	static String TAG = "SessionActivity";
	
	ListView sessionList = null;
	GroupListAdapter groupAdapter = null;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_session);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		setTitle(R.string.title_activity_session);
		String sessionId = this.getIntent().getStringExtra("sessionId");
		
		Log.i(TAG, "sessionId:" + sessionId);
		sessionId = (sessionId==null? "1" : sessionId);
		Log.i(TAG, "sessionId:" + sessionId);
		
		showSessionList(sessionId);
	}

	private void showSessionList(String sessionId) {
		List<TaskItem> list = DataUtil.convertSessionJson(getSessionList(sessionId));

		groupAdapter = new GroupListAdapter(this, list);
		
		sessionList = (ListView)findViewById(R.id.sessionListView);
		sessionList.setAdapter(groupAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.session, menu);
		return false;
	}
	
	public List<SessionItem> getSessionList(String s){
		Gson gson = new Gson();
		String content = "";
		String fileName = "";
		String urlStr = "";
		if("1".equalsIgnoreCase(s)){
			fileName = "posterSession1Json.txt";
			urlStr = Constant.SESSION_URL1;
		}else{
			fileName = "posterSession2Json.txt";
			urlStr = Constant.SESSION_URL2;
		}
		//read file and get the content
		FileInputStream fis = null;
		byte[] buffer = null;
		try {
			fis = openFileInput(fileName);
			buffer = new byte[fis.available()];
			fis.read(buffer);
			content = new String(buffer);
			
			Log.i(TAG,"读取原有文件" + fileName);
		} catch (FileNotFoundException e) {
			Log.i(TAG, "文件没找到" + fileName);
			content = updateFile(urlStr,fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<SessionItem> retList = gson.fromJson(content,new TypeToken<List<SessionItem>>(){}.getType());
		return retList;
	}
	
	private String updateFile(String urlStr, String fileName) {
		byte[] buffer;
		String content = "";
		HttpDownloader download = new HttpDownloader();
		InputStream in = download
				.getInputStreamFromURL(urlStr);
		try {
			
			FileOutputStream out = openFileOutput(fileName, MODE_APPEND);
			ByteArrayOutputStream outStream=new ByteArrayOutputStream();
			buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				outStream.write(buffer, 0, len);
			}
			out.close();
			outStream.close();
			content = new String(outStream.toByteArray());
			
			Log.i(TAG, "写入文件完毕" + fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return content;
	}
}
