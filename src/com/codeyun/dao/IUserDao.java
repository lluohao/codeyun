package com.codeyun.dao;

import com.codeyun.dao.doo.User;

public interface IUserDao {
	User findById(int id);
	int insertUser(User user);
}
