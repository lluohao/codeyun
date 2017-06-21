package com.codeyun.dao;

import com.codeyun.web.vo.User;

public interface IUserDao {
	User findById(int id);
}
