package com.codeyun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyun.dao.IUserDao;
import com.codeyun.dao.factory.MybatisBeanFactory;
import com.codeyun.web.vo.BasicView;

@Controller
public class UserController {
	@RequestMapping("/hello")
	public @ResponseBody
	Object hello(int id) {
		IUserDao dao = MybatisBeanFactory.getDao(IUserDao.class);
		System.out.println(dao);
		return dao.findById(id);
	}

	@RequestMapping("/register")
	public @ResponseBody
	BasicView register(@RequestParam String name, @RequestParam String phone,
			@RequestParam String password, @RequestParam int code, String email) {
		BasicView view = new BasicView();
		
		return view;
	}
}
