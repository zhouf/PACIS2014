package com.zhouf.pacis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.zhouf.entity.TaskItem;
import com.zhouf.util.DataUtil;

public class ContactActivity extends Activity {

	private static String TAG = "Constant";
	ListView contactList = null;
	SimpleAdapter listItemAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		contactList = (ListView) findViewById(R.id.contactListView);
		listItemAdapter = getListData();
		contactList.setAdapter(listItemAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.contact, menu);
		return false;
	}

	private SimpleAdapter getListData() {
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		List<TaskItem> tasks = prepareData();
		int i = 0;
		for (TaskItem t : tasks) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("contact_title", t.getTitle());
			map.put("contact_text", t.getSubtitle());
			listItem.add(map);// 添加到listItem中
			Log.i(TAG, "获得第" + (++i) + "个数据：" + t.getTitle());
		}

		return new MySimpleAdapter(this,// this是当前Activity的对象
				listItem,// 数据源 为填充数据后的ArrayList类型的对象
				R.layout.contact_list_item,// 子项的布局.xml文件名
				new String[] { "contact_title", "contact_text" },
				// 这个String数组中的元素就是list对象中的列，list中有几这个数组中就要写几列。

				// 值是对应XML布局文件中的一个ImageView,三个TextView的id
				new int[] { R.id.contactTitle, R.id.contactText });
	}
	
	class MySimpleAdapter extends SimpleAdapter{
		public MySimpleAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
		}
		@Override
		public boolean isEnabled(int position) {
			return false;
		}
	}



	private List<TaskItem> prepareData() {
		List<TaskItem> retList = new ArrayList<TaskItem>();

		TaskItem item1 = new TaskItem();
		item1.setTitle("For general questions, please send your enquiry to:");
		item1.setSubtitle("Prof Qing Li,\n555 Liutai Dadao, Wenjiang, Chengdu\ncontactus@pacis2014.org");

		TaskItem item2 = new TaskItem();
		item2.setTitle("For visa application-related enquiries, please contact:");
		item2.setSubtitle("Ms Wenjin Zhao,\njanetzhaozwj@gmail.com");

		TaskItem item3 = new TaskItem();
		item3.setTitle("Please send your enquiry of website to:");
		item3.setSubtitle("Ms Ling Liu,\n555 Liutai Dadao, Wenjiang, Chengdu\nliuling@pacis2014.org");

		retList.add(item1);
		retList.add(item2);
		retList.add(item3);
		
		return retList;
	}

}
