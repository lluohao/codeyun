package com.codeyun.common.sms;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import com.codeyun.common.log.CodeYunLogger;
import com.codeyun.common.pass.MD5Encoder;
import com.codeyun.common.util.DateUtil;
import com.codeyun.common.util.HttpUtil;

public class MiaoDiSmsSender implements ISmsSender {
	private static final String ACCOUNT_SID = "2e9f0f15c5ee49c5a6fff17969062a8f";
	private static final String AUTH_TOKEN = "027495a77a414b36ac843d564b01be57";
	private static URL url = null;
	static {
		try {
			url = new URL(
					"https://api.miaodiyun.com/20150822/industrySMS/sendSMS");
		} catch (MalformedURLException e) {
			CodeYunLogger.log("create url error");
		}
	}

	@Override
	public SmsResponse send(SmsRequest request) {
		String timestamp = DateUtil.toString(new Date(), "yyyyMMddHHmmss");
		String sig = MD5Encoder.md5(ACCOUNT_SID + AUTH_TOKEN + timestamp);
		String data = "accountSid=" + ACCOUNT_SID + "&to=" + request.getPhone()
				+ "&timestamp=" + timestamp + "&sig=" + sig + "&smsContent="
				+ request.getContent();
		String str = HttpUtil.post(url.toString(), data);
		CodeYunLogger.log(str);
		ObjectMapper mapper = new ObjectMapper();
		SmsResponse res = new SmsResponse();
		try {
			JsonNode root = mapper.readTree(str);
			String code = root.path("respCode").asText();
			if("00000".equals(code)){
				res.setSuccess(true);
				res.setRespCode("00000");
			}else{
				res.setSuccess(false);
				res.setRespCode(code);
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res.setSuccess(false);
			return res;
		}
		// CloseableHttpClient client = HttpClientBuilder.create().build();
		// HttpPost post = new HttpPost();
		// List<NameValuePair> data = new ArrayList<>();
		// data.add(new BasicNameValuePair("accountSid", ACCOUNT_SID));
		// data.add(new BasicNameValuePair("to", request.getPhone()));
		// data.add(new BasicNameValuePair("timestamp", timestamp));
		// data.add(new BasicNameValuePair("sig", sig));
		// try {
		// data.add(new BasicNameValuePair("smsContent",
		// URLEncoder.encode(request.getContent(), "UTF-8")));
		// post.setURI(url.toURI());
		// post.setEntity(new UrlEncodedFormEntity(data));
		// CloseableHttpResponse response = client.execute(post);
		// int code = response.getStatusLine().getStatusCode();
		// CodeYunLogger.log(code+"");
		// InputStream is = response.getEntity().getContent();
		// CodeYunLogger.log(StringUtil.fromStream(is));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
