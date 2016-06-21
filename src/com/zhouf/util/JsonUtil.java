package com.zhouf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhouf.entity.Section;
import com.zhouf.entity.SessionItem;
import com.zhouf.pacis.MainActivity;

public class JsonUtil {
	private static String TAG = "JsonUtil";
	
	@SuppressLint("SdCardPath")
	@SuppressWarnings("resource")
	public static List<Section> getRootList2(){
		Gson gson = new Gson();
		String content = "";
		//read file and get the content
		FileInputStream fis = null;
		byte[] buffer = null;
		try {
			File file = new File("/data/data/com.zhouf.pacis/files/programJson.txt");
			if(file.exists()){
				Log.i(TAG,"文件不存在，创建文件:" + file.getAbsolutePath());
				
			}
			fis = new FileInputStream(file);
			//fis = openFileInput("programJson.txt");
			buffer = new byte[fis.available()];
			fis.read(buffer);
			
			content = new String(buffer);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Section> retList = gson.fromJson(content,new TypeToken<List<Section>>(){}.getType());
		return retList;
	}

	

	/**
	 * 返回Session文件中的数据
	 * @param s
	 * @return
	 */
	public static List<SessionItem> getSessionList2(String s){
		Gson gson = new Gson();
		String content = "";
		//read file and get the content
		FileInputStream fis = null;
		byte[] buffer = null;
		try {
			File file = new File("/data/data/com.zhouf.pacis/files/posterSession"+s+"Json.txt");
			fis = new FileInputStream(file);
			//fis = openFileInput("programJson.txt");
			buffer = new byte[fis.available()];
			fis.read(buffer);
			
			content = new String(buffer);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<SessionItem> retList = gson.fromJson(content,new TypeToken<List<SessionItem>>(){}.getType());
		return retList;
	}
}
