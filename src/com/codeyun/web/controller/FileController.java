package com.codeyun.web.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.codeyun.common.util.DateUtil;
import com.codeyun.dao.doo.User;
import com.codeyun.service.IFileService;
import com.codeyun.service.impl.FileServiceImpl;
import com.codeyun.web.vo.AbstractFile;
import com.codeyun.web.vo.BasicView;
import com.codeyun.web.vo.FileView;
import com.codeyun.web.vo.FolderView;

@Controller
public class FileController {
	private IFileService fileService = new FileServiceImpl();
	@RequestMapping("/move")
	public @ResponseBody BasicView move(@RequestParam String path, @RequestParam String target, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// return new BasicView(101);
			user = new User();
			user.setPhone("15773135227");
		}
		File pathFile = new File(IFileService.root+user.getPhone()+"/"+path);
		if(!pathFile.exists()){
			new BasicView(102);
		}
		boolean ok = fileService.moveTo(pathFile, new File(IFileService.root+user.getPhone()+"/"+target));
		return new BasicView(ok?100:103);
	}
	
	@RequestMapping("/rename")
	public @ResponseBody BasicView rename(@RequestParam String file, @RequestParam String newName, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// return new BasicView(101);
			user = new User();
			user.setPhone("15773135227");
		}
		File pathFile = new File(IFileService.root+user.getPhone()+"/"+file);
		if(!pathFile.exists()){
			new BasicView(102);
		}
		boolean ok = pathFile.renameTo(new File(pathFile.getParent()+"/"+newName));
		return new BasicView(ok?100:103);
	}
	
	@RequestMapping("/list")
	public @ResponseBody FolderView list(@RequestParam(defaultValue = "") String path, HttpSession session) {
		User user = (User) session.getAttribute("user");
		FolderView view = new FolderView();
		if (user == null) {
			// return new BasicView(101);
			user = new User();
			user.setPhone("15773135227");
		}
		File target = new File(IFileService.root + "/" + user.getPhone() + "/"
				+ path);
		if(!target.exists()){
			view.setCode(102);
			return view;
		}
		if (target.isDirectory()) {
			List<AbstractFile> list = new ArrayList<>();
			File[] files = target.listFiles();
			if (files != null && files.length > 0) {
				for (File file : files) {
					FileView oneFile = new FileView();
					oneFile.setFile(file.isFile());
					oneFile.setName(file.getName());
					oneFile.setTime(DateUtil.toString(new Date(file.lastModified()), "yyyy-MM-dd HH:mm:ss"));
					oneFile.setSize(file.isFile()?file.length():-1);
					list.add(oneFile);
				}
			}
			view.setFileList(list);
			view.setSize(list.size());
			view.setCode(100);
			return view;
		}
		view.setCode(103);
		return view;
	}

	@RequestMapping("/upload")
	public @ResponseBody
	BasicView upload(MultipartFile file,
			@RequestParam(defaultValue = "") String path, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// return new BasicView(101);
			user = new User();
			user.setPhone("15773135227");
		}
		File folder = new File(IFileService.root + "/" + user.getPhone() + "/"
				+ path);
		if (folder.exists() && folder.isFile()) {
			return new BasicView(102);
		}
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File realFile = new File(IFileService.root + "/" + user.getPhone() + "/"
				+ path + "/" + file.getOriginalFilename());
		if (realFile.exists()) {
			realFile = new File(realFile.getAbsolutePath() + "-"
					+ System.currentTimeMillis());
		}
		try {
			file.transferTo(realFile);
		} catch (Exception e) {
			return new BasicView(103);
		}
		return new BasicView(100);
	}

	@RequestMapping("/newFolder")
	public @ResponseBody
	BasicView createFolder(@RequestParam(defaultValue = "") String path,
			@RequestParam String newFolder,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// return new BasicView(101);
			user = new User();
			user.setPhone("15773135227");
		}
		File folder = new File(IFileService.root + "/" + user.getPhone() + "/"
				+ path);
		if (folder.exists() && folder.isFile()) {
			return new BasicView(102);
		}
		File file = new File(folder.getAbsolutePath() + "/" + newFolder);
		boolean ok = file.mkdirs();
		return new BasicView(ok?100:103);
	}

	@RequestMapping("/delete")
	public @ResponseBody
	BasicView createFolder(@RequestParam String path,
			@RequestParam(defaultValue = "false") boolean force,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// return new BasicView(101);
			user = new User();
			user.setPhone("15773135227");
		}
		File file = new File(IFileService.root + "/" + user.getPhone() + "/" + path);
		if (!file.exists()) {
			return new BasicView(102);
		}
		if (file.isFile()) {
			file.delete();
			return new BasicView(100);
		}
		if (file.list().length > 0) {
			if (force) {
				fileService.removeAllFile(file);
				return new BasicView(100);
			} else {
				return new BasicView(103);
			}
		} else {
			file.delete();
			return new BasicView(100);
		}
	}
}
