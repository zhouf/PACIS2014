package com.zhouf.util;

import java.util.List;

import com.zhouf.entity.TaskItem;
import com.zhouf.pacis.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupListAdapter extends ArrayAdapter<TaskItem> {
	static String TAG = "GroupListAdapter";
	
	//��ű�ǩ���б������ж������������
    //����������ڱ�ǩ�б��У����Ǳ�ǩ�������������
    public GroupListAdapter(Context context, List<TaskItem> objects) {
        super(context, 0, objects);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	 View view = convertView;
    	 //Log.i(TAG,"getItem(position).getTitle():" + getItem(position).getTitle());
    	 
         //���ݱ�ǩ���ͼ��ز�ͨ�Ĳ���ģ��
         if(getItem(position).isGroup()){
             //����Ǳ�ǩ��
             view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item_tag, null);
             //��ʾ����
             TextView textView = (TextView) view.findViewById(R.id.group_list_item_text);
             textView.setText(getItem(position).getTitle());

         }else{              
             //���������������      
             view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item, null);
             //��ʾ����
             TextView textViewTitle = (TextView) view.findViewById(R.id.ItemTitle);
             TextView textViewSubTitle = (TextView) view.findViewById(R.id.ItemText);
             ImageView imgView = (ImageView)view.findViewById(R.id.ItemImage);
             
             TaskItem item = getItem(position);
             textViewTitle.setText(item.getTitle());
             textViewSubTitle.setText(item.getSubtitle());
             if(item.isMore()){
            	 imgView.setImageResource(R.drawable.more);
             }
             
         }
         //������д��view
         return view;
    }
    
    @Override
    public boolean isEnabled(int position) {
    	if(getItem(position).isGroup()){
    		return false;
        }else{
        	TaskItem item = getItem(position);
        	if(item.getTitle().contains(Constant.SESSION_KEYWORDS)){
        		return true;
        	}else if(!item.isMore()){
        		return false;
        	}
        }
    	return super.isEnabled(position);
    }
}
