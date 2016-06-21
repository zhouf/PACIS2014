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
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhouf.entity.Items;
import com.zhouf.entity.Section;
import com.zhouf.entity.TaskItem;
import com.zhouf.util.Constant;
import com.zhouf.util.DataUtil;
import com.zhouf.util.GroupListAdapter;
import com.zhouf.util.HttpDownloader;

public class MainActivity extends Activity {
	static String TAG = "MainActivity";
	ListView mylist = null;
	SimpleAdapter listItemAdapter = null;
	GroupListAdapter groupAdapter = null;
	List<TaskItem> tasklist = null;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}

		setTitle(R.string.first_title);

		// mylist = (ListView)findViewById(R.id.firstList);
		// listItemAdapter = getListData();
		// mylist.setAdapter(listItemAdapter);

		final List<TaskItem> list = DataUtil.convertJson(getRootList());

		groupAdapter = new GroupListAdapter(this, list);

		mylist = (ListView) findViewById(R.id.firstList);
		mylist.setAdapter(groupAdapter);
		mylist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			// ��д������Ӧ
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Items it = (Items) (list.get(arg2).getExtend());
				if (it.getTitle().contains(Constant.SESSION_KEYWORDS)) {
					Log.i(TAG, "session mode..." + it.getTitle());
					String title = it.getTitle().trim();
					int titleLen = title.length();
					String sessionId = title.substring(titleLen - 1, titleLen);

					Intent sessionIntent = new Intent(MainActivity.this, SessionActivity.class);
					sessionIntent.putExtra("sessionId", sessionId);
					startActivity(sessionIntent);
				} else if (it.getArticle() != null && it.getArticle().size() > 0) {
					Intent secIntent = new Intent(MainActivity.this, SecondActivity.class);
					secIntent.putExtra("item", it);
					startActivity(secIntent);
				} else {
					// �򿪵�ͼҳ��
					Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);
					mapIntent.putExtra("mapTitle", it.getTitle());
					mapIntent.putExtra("mapFile", it.getMap());
					startActivity(mapIntent);
				}

			}
		});

	}

	/*
	 * private SimpleAdapter getListData() { ArrayList<HashMap<String, Object>>
	 * listItem = new ArrayList<HashMap<String, Object>>(); List<TaskItem> tasks
	 * = DataUtil.listAll(); tasklist = tasks; int i = 0; for(TaskItem t :
	 * tasks){ HashMap<String, Object> map = new HashMap<String, Object>();
	 * map.put("ItemImage", R.drawable.task32);// ͼ����ԴͼƬ����ʾ��С���Ҷ�
	 * map.put("ItemTitle", t.getTitle()); map.put("ItemText", t.getSubtitle());
	 * listItem.add(map);// ��ӵ�listItem�� Log.i(TAG,"��õ�" + (++i) + "�����ݣ�" +
	 * t.getTitle()); }
	 * 
	 * return new SimpleAdapter( this,// this�ǵ�ǰActivity�Ķ��� listItem,// ����Դ
	 * Ϊ������ݺ��ArrayList���͵Ķ��� R.layout.group_list_item,// ����Ĳ���.xml�ļ��� new
	 * String[] { "ItemImage", "ItemTitle", "ItemText" },
	 * //���String�����е�Ԫ�ؾ���list�����е��У�list���м���������о�Ҫд���С�
	 * 
	 * //ֵ�Ƕ�ӦXML�����ļ��е�һ��ImageView,����TextView��id new int[] { R.id.ItemImage,
	 * R.id.ItemTitle, R.id.ItemText}); }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	public List<Section> getRootList() {
		Gson gson = new Gson();
		String content = "";
		// read file and get the content
		FileInputStream fis = null;
		byte[] buffer = null;
		String fileName = "programJson.txt";
		try {

			fis = openFileInput(fileName);
			// fis = openFileInput("programJson.txt");
			buffer = new byte[fis.available()];
			fis.read(buffer);
			content = new String(buffer);

			Log.i(TAG,"��ȡԭ���ļ�" + fileName);
		} catch (FileNotFoundException e) {
			Log.i(TAG, "�ļ�û�ҵ�" + fileName);
			
			String urlStr = Constant.JSON_URL1;
			content = updateFile(urlStr,fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Section> retList = gson.fromJson(content, new TypeToken<List<Section>>() {
		}.getType());
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
			
			Log.i(TAG, "д���ļ����" + fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return content;
	}

}
