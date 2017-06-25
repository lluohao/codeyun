package com.codeyun.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {
	public static String fromStream(InputStream is, String charset) throws IOException{
		StringBuilder sb = new StringBuilder();
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
		while((line=reader.readLine())!=null){
			sb.append(line+"\n");
		}
		return sb.toString();
	}

	public static String fromStream(InputStream is) throws IOException{
		return fromStream(is, "UTF-8");
	}
}
