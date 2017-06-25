package com.codeyun.common.pass;

import com.codeyun.common.log.CodeYunLogger;

public class Test {
	@org.junit.Test
	public void test(){
		String str = MD5Encoder.md5("ad");
		CodeYunLogger.log(str);
	}
}
