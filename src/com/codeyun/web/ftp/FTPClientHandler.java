package com.codeyun.web.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author onroadrui 用于处理控制连接数据请求的线程 控制连接:在创建之后，直到socket.close()(四次挥手的过程)，
 *         都是tcp里面的establish的阶段。可以自由地传输数据（全双工的）
 * */
public class FTPClientHandler extends Thread {

	private Socket socket;
	private String userName;
	private boolean login;
	private static Map<String, Integer> counts = new HashMap<String, Integer>();

	public FTPClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		System.out.println("hello");
		BufferedReader reader;
		UserCommond uc = new UserCommond();
		try {
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			Integer count = counts.get(socket.getInetAddress().toString());
			// 第一次访问，输入流里面是没有东西的，所以会阻塞住
			if (count == null || count == 0) {
				writer.write("200 \r\n");
				writer.write("530 \r\n");
				writer.flush();
				counts.put(socket.getInetAddress().toString(), 1);
			} else {
				// 两种情况会关闭连接：(1)quit命令 (2)密码错误
				if (!socket.isClosed()) {
					String line = reader.readLine();
					if (line != null) {
						String[] tempArr = line.split(" ");
						String cmd = tempArr[0];
						if ("USER".equals(cmd.toUpperCase())) {
							String result = uc.handler(this, line);
							writer.write(result);
							writer.write("\r\n");
							writer.flush();
						}
					}
				} else {
					// 连接已经关闭，这个线程不再有存在的必要
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("结束tcp连接");
		}

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

}