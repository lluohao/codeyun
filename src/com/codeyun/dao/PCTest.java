package com.codeyun.dao;

import java.sql.Date;

import com.codeyun.dao.doo.PhoneCodeCountSelecter;
import com.codeyun.dao.factory.MybatisBeanFactory;

public class PCTest {
	public static void main(String[] args) {
		IPhoneCodeDao dao = MybatisBeanFactory.getDao(IPhoneCodeDao.class);
//		PhoneCode pc = new PhoneCode();
//		pc.setCphone("18570621205");
//		pc.setCtime(new Date(System.currentTimeMillis()));
//		pc.setCvalue("100861");
//		int count = dao.addPhoneCode(pc);
//		System.out.println(count);
//		System.out.println(pc.getCid());
		PhoneCodeCountSelecter selecter = new PhoneCodeCountSelecter();
		selecter.setDate(new Date(System.currentTimeMillis()));
		selecter.setPhone("18570621205");
		System.out.println(dao.count(selecter));
	}
}
