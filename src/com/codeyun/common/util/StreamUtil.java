package com.codeyun.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {
	public static int copy(InputStream in, OutputStream out) throws IOException {
		byte[] buff = new byte[1024];
		int len = 0;
		int sum = 0;
		while ((len = in.read(buff)) > 0) {
			out.write(buff, 0, len);
			sum += len;
		}
		return sum;
	}
}
