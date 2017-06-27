package com.codeyun.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String toString(Date date, String p){
		SimpleDateFormat format = new SimpleDateFormat(p);
		return format.format(date);
	}
	
	public static Date format(String date, String p){
		SimpleDateFormat format = new SimpleDateFormat(p);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
