package com.codeyun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyun.web.vo.User;
@Controller
public class UserController {
	@RequestMapping("/hello")
	public @ResponseBody Object hello(User user){
		return user;
	}
}
