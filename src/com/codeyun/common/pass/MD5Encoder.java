package com.codeyun.common.pass;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Encoder {
	public static String md5(String base){
		return DigestUtils.md5Hex(base.getBytes());
	}
}
