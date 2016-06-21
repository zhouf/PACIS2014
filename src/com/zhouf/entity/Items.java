package com.zhouf.entity;

import java.io.Serializable;
import java.util.List;

public class Items implements Serializable {

	private String title;
	private String desc;
	private String map;
	private List<Article> article;
	private String belongTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public List<Article> getArticle() {
		return article;
	}
	public void setArticle(List<Article> article) {
		this.article = article;
	}
	public String getBelongTime() {
		return belongTime;
	}
	public void setBelongTime(String belongTime) {
		this.belongTime = belongTime;
	}
	
}
