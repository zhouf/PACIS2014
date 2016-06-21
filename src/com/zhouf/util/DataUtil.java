package com.zhouf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.util.Log;

import com.zhouf.entity.Article;
import com.zhouf.entity.Items;
import com.zhouf.entity.Section;
import com.zhouf.entity.SessionItem;
import com.zhouf.entity.TaskItem;

public class DataUtil {
	
	static String TAG = "DataUtil";

	//for test...
	public static List<TaskItem> listAll(){
		List<TaskItem> retList = new ArrayList<TaskItem>();
		for (int i = 0; i < 3; i++) {
			TaskItem item = new TaskItem();
			item.setTitle("TitleA" + i);
			item.setSubtitle("sub title " + i);
			retList.add(item);
		}
		for (int i = 0; i < 6; i++) {
			TaskItem item = new TaskItem();
			item.setTitle("TitleB" + i);
			item.setSubtitle("sub title " + i);
			retList.add(item);
		}
		return retList;
	}
	
	/**
	 * 将JSon数据转化为列表项
	 * @return
	 */
	public static List<TaskItem> convertJson(List<Section> sectionList){
		List<TaskItem> retList = new ArrayList<TaskItem>();
		
		//List<Section> sectionList = JsonUtil.getRootList();
		for(Section section : sectionList){
			TaskItem groupItem = new TaskItem();
			String timeStr = convertDateStr(section.getDate()) + section.getTime();
			groupItem.setTitle(timeStr);
			groupItem.setGroup(true);
			groupItem.setExtend(section);
			retList.add(groupItem);

			if(section.getItems()!=null){
				for(Items scheItem : section.getItems()){
					TaskItem tItem = new TaskItem();
					tItem.setTitle(scheItem.getTitle());
					tItem.setSubtitle(scheItem.getDesc());
					tItem.setGroup(false);
					
//					Log.i(TAG,"Title:" + scheItem.getTitle());
//					Log.i(TAG,"Article:" + scheItem.getArticle());
					if(scheItem.getTitle().contains(Constant.SESSION_KEYWORDS)){
						tItem.setMore(true);
					}else{
						//当存在文件列表或是有地图时，设置为可点击
						boolean isMore = false;
						if(scheItem.getArticle()!=null && scheItem.getArticle().size()>0){
							isMore = true;
						}else if(scheItem.getMap()!=null && scheItem.getMap().length()>0){
							isMore = true;
						}
						tItem.setMore(isMore);
					}
					
					scheItem.setBelongTime(timeStr);
					tItem.setExtend(scheItem);
					retList.add(tItem);
				}
			}
		}
		return retList;
	}
	
	/**
	 * 将JSon数据转化为列表项
	 * @param s 标记session1 or session2
	 * @return
	 */
	public static List<TaskItem> convertSessionJson(List<SessionItem> sectionList){
		List<TaskItem> retList = new ArrayList<TaskItem>();
		
		for(SessionItem session : sectionList){
			TaskItem groupItem = new TaskItem();
			groupItem.setTitle(session.getSection());
			groupItem.setGroup(true);
			groupItem.setExtend(groupItem);
			retList.add(groupItem);

			if(session.getArticle()!=null){
				for(Article articleItem : session.getArticle()){
					TaskItem tItem = new TaskItem();
					tItem.setTitle(articleItem.getTitle());
					tItem.setSubtitle(articleItem.getAuthors());
					tItem.setGroup(false);
					
//					Log.i(TAG,"Title:" + articleItem.getTitle());
//					Log.i(TAG,"Authors:" + articleItem.getAuthors());
					tItem.setMore(false);
					
					tItem.setExtend(articleItem);
					retList.add(tItem);
				}
			}
		}
		return retList;
	}

	/**
	 * 修改日期字串格式
	 * @param date 2011-01-01
	 * @return WED 01-01
	 */
	@SuppressLint("SimpleDateFormat")
	private static String convertDateStr(String date) {
		String retStr = "";
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfOutput = new SimpleDateFormat("E,dd MMM ");
		try {
			retStr = sdfOutput.format(sdfInput.parse(date));
		} catch (ParseException e) {
			retStr = date;
		}
		return retStr;
	}

	/**
	 * 将文章转换为列表显示
	 * @param articles
	 * @return
	 */
	public static List<TaskItem> listArticles(List<Article> articles) {
		List<TaskItem> retList = new ArrayList<TaskItem>();
		for(Article a : articles){
			TaskItem ti = new TaskItem();
			ti.setTitle(a.getTitle());
			ti.setSubtitle(a.getAuthors());
			ti.setGroup(false);
			//ti.setMore(a.getFileName()!=null);
			ti.setMore(false);
			
			retList.add(ti);
		}
		return retList;
	}
}
