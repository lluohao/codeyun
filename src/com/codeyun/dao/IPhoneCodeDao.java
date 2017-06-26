package com.codeyun.dao;

import com.codeyun.dao.doo.PhoneCode;
import com.codeyun.dao.doo.PhoneCodeCountSelecter;

public interface IPhoneCodeDao {
	Integer addPhoneCode(PhoneCode pc);
	Integer count(PhoneCodeCountSelecter selecter);
}
