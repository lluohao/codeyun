package com.codeyun.web.vo;

import java.util.ArrayList;
import java.util.List;

public class FolderView extends AbstractFile{
	private List<AbstractFile> fileList = new ArrayList<>();

	public FolderView() {
		this.setFile(false);
	}
	public List<AbstractFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<AbstractFile> fileList) {
		this.fileList = fileList;
	}
	
}
