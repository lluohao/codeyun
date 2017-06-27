package com.codeyun.service.impl;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import com.codeyun.common.log.CodeYunLogger;
import com.codeyun.common.sms.ISmsSender;
import com.codeyun.common.sms.MiaoDiSmsSender;
import com.codeyun.common.sms.SmsRequest;
import com.codeyun.common.sms.SmsResponse;
import com.codeyun.common.util.StringUtil;
import com.codeyun.dao.IPhoneCodeDao;
import com.codeyun.dao.doo.PhoneCode;
import com.codeyun.dao.doo.PhoneCodeCountSelecter;
import com.codeyun.dao.factory.MybatisBeanFactory;
import com.codeyun.service.IPhoneCodeService;
import com.codeyun.web.vo.PhoneCodeView;

public class PhoneCodeServiceImpl implements IPhoneCodeService {
	private IPhoneCodeDao phoneCodeDao = MybatisBeanFactory
			.getDao(IPhoneCodeDao.class);
	private ISmsSender sender = new MiaoDiSmsSender();
	private static PhoneCodeServiceImpl instance;

	public PhoneCodeServiceImpl() {
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
		PhoneCodeView view = new PhoneCodeView();
		if (!StringUtil.isPhoneNumber(phone)) {
			view.setCode(101);
			return view;
		}
		Long last = (Long) session.getAttribute("phonecode.lasttime");
		CodeYunLogger.log(last);
		CodeYunLogger.log(System.currentTimeMillis());
		if (last != null && System.currentTimeMillis() - last <= 60000) {
			view.setCode(102);
			return view;
		}
		PhoneCodeCountSelecter selecter = new PhoneCodeCountSelecter();
		selecter.setPhone(phone);
		selecter.setDate(new Date(System.currentTimeMillis()));
		int count = phoneCodeDao.count(selecter);
		if (count >= 5) {
			view.setCode(103);
		}
		int code = (int) (Math.random() * 900000 + 100000);
		SmsRequest request = new SmsRequest();
		request.setContent("【天码云盘】验证码：" + code + "，打死都不要告诉别人哦！");
		request.setPhone(phone);
		SmsResponse response = sender.send(request);
		if (response.isSuccess()) {
			session.setAttribute("phonecode.value", code);
			session.setAttribute("phonecode.phone", phone);
			session.setAttribute("phonecode.use", false);
			session.setAttribute("phonecode.lasttime", selecter.getDate()
					.getTime());
			view.setCode(100);
			PhoneCode pc = new PhoneCode();
			pc.setCphone(phone);
			pc.setCtime(selecter.getDate());
			pc.setCvalue(code + "");
			int line = phoneCodeDao.addPhoneCode(pc);
			if (line == 0) {
				CodeYunLogger.log("SMS:INSERT INTO DATABASE FAILURE");
			}
		} else {
			view.setCode(104);
			CodeYunLogger.log("SMS:ERROR" + phone + ","
					+ response.getRespCode());
		}
		return view;
	}

}
