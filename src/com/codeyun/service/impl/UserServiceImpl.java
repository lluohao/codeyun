package com.codeyun.service.impl;

import com.codeyun.dao.IUserDao;
import com.codeyun.dao.doo.User;
import com.codeyun.dao.factory.MybatisBeanFactory;
import com.codeyun.service.IUserService;

public class UserServiceImpl implements IUserService{
	private IUserDao userDao = MybatisBeanFactory.getDao(IUserDao.class);
	@Override
	public User insertUser(String name, String phone, String password,
			String email) {
		User user = new User();
		user.setName(name);
		return null;
	}

}
