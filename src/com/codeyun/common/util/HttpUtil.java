package com.codeyun.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * http请求工具
 */
public class HttpUtil{
	public static String post(String url, String body){
		String result = "";
		try{
			OutputStreamWriter out = null;
			BufferedReader in = null;
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// 设置连接参数
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(20000);

			// 提交数据
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(body);
			out.flush();

			// 读取返回数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			boolean firstLine = true; 
			while ((line = in.readLine()) != null){
				if (firstLine){
					firstLine = false;
				} else{
					result += System.lineSeparator();
				}
				result += line;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
