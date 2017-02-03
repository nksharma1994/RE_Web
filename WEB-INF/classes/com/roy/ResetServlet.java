package com.roy;
import Util.*;

import java.io.*;
import java.sql.*;

import javax.servlet.http.*;
import javax.servlet.*;
public class ResetServlet extends HttpServlet {
	PreparedStatement ps = null;
	Connection con = null;
	String pw;
	String id;
	int count;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		id=request.getParameter("uid");
	    pw=request.getParameter("newpw");
	//if(s!=0 && pass!=0 && nwpass!=0){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/roydb";
			con=DriverManager.getConnection(url,"root","FgYuy*(oiTR");
			String sql= "update admin set password=? where aid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,pw);
			ps.setString(2,id);
			 count= ps.executeUpdate();
			if (count!=0) {
		out.write("Password Updated Succesfully !!!"+"<a href='index.html'>Home</a>");		
			}else{
				out.write("<font color='YellowGreen'>Please enter correct credential"+"<a href='index.html'>Home</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		JdbcUtil.cleanup(ps,con);
		}
		}
	}


