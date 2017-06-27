package com.codeyun.service.impl;

@SuppressWarnings("serial")
public class BasicCodeException extends RuntimeException {
	private int code;

	public BasicCodeException(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
