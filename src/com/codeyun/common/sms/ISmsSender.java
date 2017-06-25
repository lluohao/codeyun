package com.codeyun.common.sms;

public interface ISmsSender {
	SmsResponse send(SmsRequest request);
}
