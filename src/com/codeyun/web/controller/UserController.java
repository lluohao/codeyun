package com.codeyun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyun.dao.IUserDao;
import com.codeyun.dao.factory.MybatisBeanFactory;
@Controller
public class UserController {
	@RequestMapping("/hello")
	public @ResponseBody Object hello(int id){
		IUserDao dao = MybatisBeanFactory.getDao(IUserDao.class);
		System.out.println(dao);
		return dao.findById(id);
	}
}
