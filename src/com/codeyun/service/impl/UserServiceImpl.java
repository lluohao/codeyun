package com.codeyun.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.codeyun.common.pass.MD5Encoder;
import com.codeyun.common.util.DateUtil;
import com.codeyun.common.util.StringUtil;
import com.codeyun.dao.IUserDao;
import com.codeyun.dao.doo.User;
import com.codeyun.dao.factory.MybatisBeanFactory;
import com.codeyun.service.IUserService;

public class UserServiceImpl implements IUserService {
	private IUserDao userDao = MybatisBeanFactory.getDao(IUserDao.class);

	@Override
	public User insertUser(String name, String phone, String password,
			String email) {
		// 验证数据合法性
		if (StringUtil.inLen(name, 3, 20) && StringUtil.inLen(password, 32, 32)
				&& StringUtil.isPhoneNumber(phone)) {
			boolean isEmail = StringUtil.isEmail(email);
			if (isEmail || StringUtil.isEmpty(email)) {
				// 验证占用信息
				if (userDao.findByPhone(phone) != null) {
					throw new BasicCodeException(102);
				}
				if (userDao.findByName(name) != null) {
					throw new BasicCodeException(101);
				}
				if (isEmail&&userDao.findByEmail(email) != null) {
					throw new BasicCodeException(103);
				}
				User user = new User();
				user.setEmail(isEmail ? email : null);
				user.setName(name);
				user.setPhone(phone);
				user.setPassword(password);
				user.setTime(new Timestamp(System.currentTimeMillis()));
				userDao.insertUser(user);
				return user;
			}else{
				throw new BasicCodeException(105);
			}
		} else {
			throw new BasicCodeException(105);
		}
	}

	@Override
	public User login(String iden, String pass, String time, int type) {
		Date s = DateUtil.format(time, "yyyyMMddHHmmss");
		if(s==null||System.currentTimeMillis()-s.getTime()>1000*60*5){
			throw new BasicCodeException(103);
		}
		User user = type==0?userDao.findByName(iden):type==1?userDao.findByPhone(iden):userDao.findByEmail(iden);
		if(user==null){
			throw new BasicCodeException(102);
		}
		String realSig = MD5Encoder.md5(user.getPassword()+time);
		if(realSig.equals(pass)){
			return user;
		}else{
			throw new BasicCodeException(101);
		}
	}

}
