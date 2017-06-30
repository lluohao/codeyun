package com.codeyun.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyun.common.util.StringUtil;
import com.codeyun.dao.doo.User;
import com.codeyun.service.IUserService;
import com.codeyun.service.impl.BasicCodeException;
import com.codeyun.service.impl.UserServiceImpl;
import com.codeyun.web.vo.BasicView;

@Controller
public class UserController {
	private IUserService service = new UserServiceImpl();

	@RequestMapping("/register")
	public @ResponseBody
	BasicView register(@RequestParam String name, @RequestParam String phone,
			@RequestParam String password, @RequestParam int code,
			String email, HttpSession session) {
		BasicView view = new BasicView();
		Integer realCode = (Integer) session.getAttribute("phonecode.value");
		String phoneNumber = (String) session.getAttribute("phonecode.phone");
		Boolean isUse = (Boolean) session.getAttribute("phonecode.use");
		isUse = isUse == null ? true : isUse;
		if (isUse || realCode == null || code != realCode
				|| phoneNumber == null || !phoneNumber.equals(phone)) {
			view.setCode(104);
			return view;
		}
		try {
			service.insertUser(name, phone, password, email);
			view.setCode(100);
		} catch (BasicCodeException e) {
			view.setCode(e.getCode());
		}
		return view;
	}

	@RequestMapping("/login")
	public @ResponseBody
	BasicView login(String name, String email, String phone,
			@RequestParam String sig, @RequestParam String timestamp,
			HttpSession session) {
		User user = null;
		try {
			if (StringUtil.inLen(name, 3, 20)) {
				// login by name
				user = service.login(name, sig, timestamp, 0);
			}
			if (StringUtil.isPhoneNumber(phone)) {
				// login by phone number
				user = service.login(phone, sig, timestamp, 1);
			}
			if (StringUtil.isEmail(email)) {
				// login by email
				user = service.login(email, sig, timestamp, 2);
			}
			if (user != null) {
				session.setAttribute("user", user);
				return new BasicView(100);
			}
		} catch (BasicCodeException e) {
			return new BasicView(e.getCode());
		}
		return new BasicView(102);
	}
}
