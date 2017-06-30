package com.codeyun.service;

import java.io.File;

public interface IFileService {
	public static String root = "C:/data/";
	void removeAllFile(File file);

	boolean moveTo(File file, File targetFile);
	

}