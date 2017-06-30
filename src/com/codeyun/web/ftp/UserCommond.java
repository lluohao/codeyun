package com.codeyun.web.ftp;

public class UserCommond {
	public String handler(FTPClientHandler handler, String data) {
		System.out.println(data);
		if(handler.isLogin()){
			return "200 you are login";
		}
		String[] tempArr = data.split(" ");
		if(tempArr.length>2){
			handler.setName(data);
			return "331 input the password:";
		}
		handler.setName(data);
		return "332 input the name";
	}
}
