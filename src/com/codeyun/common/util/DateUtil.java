package com.codeyun.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String toString(Date date, String p){
		SimpleDateFormat format = new SimpleDateFormat(p);
		return format.format(date);
	}
}
