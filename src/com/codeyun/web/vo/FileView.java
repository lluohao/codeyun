package com.codeyun.web.vo;

public class FileView extends AbstractFile{
	private long size;
	private String icon;
	public FileView() {
		this.setFile(true);
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
