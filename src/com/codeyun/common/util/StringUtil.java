package com.codeyun.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.codeyun.common.log.CodeYunLogger;

public class StringUtil {
	public static String fromStream(InputStream is, String charset)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				charset));
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

	public static String fromStream(InputStream is) throws IOException {
		return fromStream(is, "UTF-8");
	}

	public static boolean isPhoneNumber(String phone) {
		if (phone == null || phone.length() != 11) {
			return false;
		}
		return phone.matches("1[3-9]\\d{9}");
	}

	public static boolean isEmpty(String str) {
		return str != null && str.length() > 0;
	}
	public static void main(String[] args) {
		CodeYunLogger.log(isPhoneNumber("18570621205"));
	}
}
