package com.codeyun.service;

import javax.servlet.http.HttpSession;

import com.codeyun.dao.IPhoneCodeDao;
import com.codeyun.dao.factory.MybatisBeanFactory;
import com.codeyun.web.vo.PhoneCodeView;

public class PhoneCodeServiceImpl implements IPhoneCodeService {
	private IPhoneCodeDao phoneCodeDao = MybatisBeanFactory.getDao(IPhoneCodeDao.class);

	private static PhoneCodeServiceImpl instance;

	private PhoneCodeServiceImpl() {

	}

	public static PhoneCodeServiceImpl getInstance() {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					instance = new PhoneCodeServiceImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public PhoneCodeView sendVerifiCode(String phone, HttpSession session) {
		return null;
	}

}
