package com.codeyun.dao;

import com.codeyun.dao.doo.User;

public interface IUserDao {
	User findById(int id);
	User findByName(String name);
	User findByPhone(String phone);
	User findByEmail(String email);
	int insertUser(User user);
}
