package com.codeyun.dao.factory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisBeanFactory {
	private static SqlSessionFactory factory;
	private static SqlSession session;
	static{
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(MybatisBeanFactory.class.getResourceAsStream("/conf/mybatis.xml"));
		session = factory.openSession(true);
	}
	public static <T> T getDao(Class<T> cls){
		return session.getMapper(cls);
	}
}
