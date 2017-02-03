package com.roy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import Util.*;
import javax.servlet.http.HttpServletResponse;

public class CareerServlet extends HttpServlet {
	FileInputStream fis = null;
	PreparedStatement ps = null;
	Connection con = null;
	String name;
	String email;
	String position;
	String phone;
	String resume;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("txt/html");
		PrintWriter out = resp.getWriter();
		name = req.getParameter("name");
		email = req.getParameter("email");
		phone = req.getParameter("mno");
		position = req.getParameter("psapplied");
		resume = req.getParameter("fileupload");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/roydb","root","FgYuy*(oiTR");
			String sql = "insert into career_form values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, position);
			fis = new FileInputStream(new File(resume));
			System.out.println("*************************" + fis.available());
			ps.setBinaryStream(5,fis,(int)resume.length());
		    ps.executeUpdate();
		//out.write("Thank You ," +name+ "we will response you soon !"+"<a href='index.html'>Home</a>");	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.cleanup(ps, con);
		}

	}

}
