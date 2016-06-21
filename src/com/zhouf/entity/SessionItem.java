package com.zhouf.entity;

import java.util.List;

public class SessionItem {

	private String section;
	private List<Article> article;
	
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public List<Article> getArticle() {
		return article;
	}
	public void setArticle(List<Article> article) {
		this.article = article;
	}
	
}
