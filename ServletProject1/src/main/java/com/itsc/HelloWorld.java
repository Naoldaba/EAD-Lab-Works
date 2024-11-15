package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class HelloWorld extends HttpServlet {
	protected void doGet(HttpServletRequest req,
		HttpServletResponse res) throws IOException{
		
		PrintWriter pw = res.getWriter();
		pw.println("Hello World");
	}
}