package com.codeyun.service.impl;

import java.io.File;

import com.codeyun.service.IFileService;

public class FileServiceImpl implements IFileService {
	/* (non-Javadoc)
	 * @see com.codeyun.service.impl.IFileService#removeAllFile(java.io.File)
	 */
	public  void removeAllFile(File file){
		if(file.exists()){
			if(file.isFile()){
				file.delete();
			}else{
				File[] files = file.listFiles();
				if(files!=null&&files.length>0){
					for (File file2 : files) {
						removeAllFile(file2);
					}
				}
				file.delete();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.codeyun.service.impl.IFileService#moveTo(java.io.File, java.io.File)
	 */
	public  boolean moveTo(File file, File targetFile){
		if(targetFile.exists()&&targetFile.isFile()){
			return false;
		}
		if(!file.exists()){
			return false;
		}
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		if(file.isFile()){
			file.renameTo(new File(targetFile.getAbsolutePath()+"/"+file.getName()));
		}else{
			File[] files = file.listFiles();
			if(files.length>0){
				for (File file2 : files) {
					moveTo(file2, new File(targetFile.getAbsolutePath()+"/"+file.getName()));
				}
			}
			file.delete();
		}
		return true;
	}
}
