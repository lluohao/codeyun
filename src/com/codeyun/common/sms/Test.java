package com.codeyun.common.sms;

import com.codeyun.common.log.CodeYunLogger;


public class Test {
	@org.junit.Test
	public void test(){
		ISmsSender sender = new MiaoDiSmsSender();
		SmsRequest req = new SmsRequest();
		req.setContent("【天码云盘】验证码：486257，打死都不要告诉别人哦！");
		req.setPhone("");
		SmsResponse res = sender.send(req);
		CodeYunLogger.log(res.toString());
	}
}
