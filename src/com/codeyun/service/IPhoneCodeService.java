package com.codeyun.service;

import javax.servlet.http.HttpSession;

import com.codeyun.web.vo.PhoneCodeView;

public interface IPhoneCodeService {
	PhoneCodeView sendVerifiCode(String phone, HttpSession session);
}
