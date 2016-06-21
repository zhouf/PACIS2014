package com.zhouf.entity;

public class TaskItem {

	private String title;
	private String subtitle;
	private boolean group;
	private boolean more;
	private Object extend;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public boolean isGroup() {
		return group;
	}
	public void setGroup(boolean group) {
		this.group = group;
	}
	public boolean isMore() {
		return more;
	}
	public void setMore(boolean more) {
		this.more = more;
	}
	public Object getExtend() {
		return extend;
	}
	public void setExtend(Object extend) {
		this.extend = extend;
	}
}
