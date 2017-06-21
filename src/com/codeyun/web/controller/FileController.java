package com.codeyun.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyun.web.vo.AbstractFile;
import com.codeyun.web.vo.FileView;
import com.codeyun.web.vo.FolderView;

@Controller
public class FileController {
	@RequestMapping("/myfile")
	public @ResponseBody FolderView myFile(HttpServletResponse res, @RequestParam(defaultValue="") String path){
		res.addHeader("Access-Control-Allow-Origin", "*");
		String root = "E:\\JAVA\\2015-11-27\\";
		path = root+path;
		return createFolderView(path);
	}
	
	private static FolderView createFolderView(String path){
		File file = new File(path);
		if(file.exists()){
			if(file.isDirectory()){
				FolderView view = new FolderView();
				List<AbstractFile> files = new ArrayList<>();
				File[] tt = file.listFiles();
				if(tt!=null){
					for (File one : tt) {
						if(one.isFile()){
							FileView fv = new FileView();
							fv.setName(one.getName());
							fv.setSize(one.length());
							fv.setTime(one.lastModified()+"");
							files.add(fv);
						}
						if(one.isDirectory()){
							FolderView fv = createFolderView(one.getAbsolutePath());
							fv.setName(one.getName());
							files.add(fv);
						}
					}
				}
				view.setFileList(files);
				return view;
			}
			return null;
		}
		return null;
	}
}
