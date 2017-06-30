package com.codeyun.web.vo;

import java.util.ArrayList;
import java.util.List;

public class FolderView{
	private List<AbstractFile> fileList = new ArrayList<>();
	private int size;
	private int code;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<AbstractFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<AbstractFile> fileList) {
		this.fileList = fileList;
	}
	
}
