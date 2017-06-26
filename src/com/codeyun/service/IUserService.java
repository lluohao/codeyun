package com.codeyun.service;

import com.codeyun.dao.doo.User;

public interface IUserService {
	User insertUser(String name, String phone, String password, String email);
}
