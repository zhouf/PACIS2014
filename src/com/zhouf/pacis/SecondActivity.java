package com.zhouf.pacis;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhouf.entity.Article;
import com.zhouf.entity.Items;
import com.zhouf.entity.SessionItem;
import com.zhouf.entity.TaskItem;
import com.zhouf.util.Constant;
import com.zhouf.util.DataUtil;
import com.zhouf.util.HttpDownloader;

public class SecondActivity extends Activity {
	static String TAG = "SecondActivity";
	ListView seclist = null;
	SecListAdapter secAdapter = null;
	List<TaskItem> tasklist = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		setTitle(R.string.second_title);
		
		final Items items = (Items) this.getIntent().getSerializableExtra("item");
		Log.i(TAG,"接收到参数" + items);
		Log.i(TAG,"MAP:" + items.getMap());
		
		TextView timeText = (TextView)findViewById(R.id.sec_timetext);
		TextView roomText = (TextView)findViewById(R.id.sec_roomtext);
		TextView titleText = (TextView)findViewById(R.id.sec_title);
		ImageView img = (ImageView)findViewById(R.id.sec_img_map);
		if(items.getMap()!=null && items.getMap().length()>0){
			img.setImageResource(R.drawable.map48);
			img.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					Log.i(TAG,"onClick...");
					Intent mapIntent = new Intent(SecondActivity.this,MapActivity.class);
					mapIntent.putExtra("mapTitle", items.getTitle());
					mapIntent.putExtra("mapFile", items.getMap());
					startActivity(mapIntent);
				}
			});
		}
		
		timeText.setText(items.getBelongTime());
		roomText.setText(items.getDesc());
		titleText.setText(items.getTitle());
		List<Article> articles = items.getArticle();
		Log.i(TAG,"articles.size():" + articles.size());
		
		final List<TaskItem> list = DataUtil.listArticles(articles);
		
		secAdapter = new SecListAdapter(this, list);
		
		seclist = (ListView)findViewById(R.id.secListView);
		seclist.setAdapter(secAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.second, menu);
		return false;
	}

	private static class SecListAdapter extends ArrayAdapter<TaskItem>{
        public SecListAdapter(Context context, List<TaskItem> objects) {
            super(context, 0, objects);
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	 View view = convertView;
        	 Log.i(TAG,"getItem(position).getTitle():" + getItem(position).getTitle());
        	 
             //根据标签类型加载数据项
             view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item, null);
             //显示名称
             TextView textViewTitle = (TextView) view.findViewById(R.id.ItemTitle);
             TextView textViewSubTitle = (TextView) view.findViewById(R.id.ItemText);
             ImageView imgView = (ImageView)view.findViewById(R.id.ItemImage);
             
             TaskItem item = getItem(position);
             textViewTitle.setText(item.getTitle());
             textViewSubTitle.setText(item.getSubtitle());
             if(item.isMore()){
            	 imgView.setImageResource(R.drawable.more);
             }
                 
             //返回重写的view
             return view;
        }
        
        @Override
        public boolean isEnabled(int position) {
        	TaskItem item = getItem(position);
        	if(!item.isMore()){
        		return false;
        	}
        	return super.isEnabled(position);
        }
    }
	
}
