package com.codeyun.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

public class MyDispacherServlet extends DispatcherServlet{
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String contentPath = request.getContextPath();
		System.out.println(url);
		System.out.println(contentPath);
		if(url.endsWith(".html")){
			request.getRequestDispatcher(url.replace(contentPath, "")).forward(request, response);
		}else{
			super.service(request, response);
		}
	}
}
