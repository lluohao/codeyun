package com.codeyun.common.sms;

public class SmsResponse {
	private boolean success;
	private String respCode;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	@Override
	public String toString() {
		return "SmsResponse [success=" + success + ", respCode=" + respCode
				+ "]";
	}
}
