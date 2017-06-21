package com.codeyun.web.controller;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyun.dao.IUserDao;
import com.codeyun.web.vo.User;
@Controller
public class UserController {
	@RequestMapping("/hello")
	public @ResponseBody Object hello(int id){
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(UserController.class.getResourceAsStream("/conf/mybatis.xml"));
		IUserDao dao =  factory.openSession().getMapper(IUserDao.class);
		return dao.findById(id);
	}
	public static void main(String[] args) {
		UserController controller = new UserController();
		User user = (User) controller.hello(2);
		System.out.println(user);
	
	}
}
