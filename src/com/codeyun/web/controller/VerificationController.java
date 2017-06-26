package com.codeyun.web.controller;

import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyun.common.image.GaussImageNoise;
import com.codeyun.common.image.VerificationImage;
import com.codeyun.common.util.StringUtil;
import com.codeyun.web.vo.PhoneCodeView;

@Controller
public class VerificationController {
	@RequestMapping("/verifiImage")
	public void verifiImage(@RequestParam String key,
			@RequestParam(defaultValue = "200") int width,
			@RequestParam(defaultValue = "75") int height,
			@RequestParam(defaultValue = "70") int fontSize,
			HttpSession session, HttpServletResponse res) {
		res.setContentType("image/png");
		int code = (int) (Math.random() * 900000) + 100000;
		session.setAttribute("image_" + key, code);
		VerificationImage image = new VerificationImage(width, height, code
				+ "");
		image.setFont(new Font("Times New Roman", 0, fontSize));
		GaussImageNoise noise = new GaussImageNoise();
		image.addNoise(noise);
		image.create();
		try {
			ImageIO.write(image, "jpg", res.getOutputStream());
			res.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/phoneCode")
	public @ResponseBody PhoneCodeView phoneCode(HttpSession session, @RequestParam String phone){
		return null;
	}
}
