package com.roy;
import Util.*;

import java.io.*;
import java.sql.*;

import javax.servlet.http.*;
import javax.servlet.*;
public class ForgotServlet extends HttpServlet {
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs=null;
	String mail;
	String phone;
	String email;
	String ph;
	String pass;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	 mail=request.getParameter("uid");
	phone=request.getParameter("ph");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/roydb";
			con=DriverManager.getConnection(url,"root","FgYuy*(oiTR");
			String sql = "select * from admin";
			ps=con.prepareStatement(sql);
		   // ps.setString(1,mail);
		    rs= ps.executeQuery();
			while(rs.next()) {
				email=rs.getString(3);
				ph=rs.getString(4);
				pass=rs.getString(5);
			}
			if(mail.equalsIgnoreCase(email)&& ph.equals(phone)){
		//	if(email.equals(mail) && ph.equals(phone)){
response.sendRedirect("http://promo.smsfresh.co/api/sendmsg.php?user=royelectricals&pass=123456&sender=Sectrx&phone="+ph+"&text="+"password:-"+pass+"&priority=sdnd&stype=normal");
			}else{
				RequestDispatcher rd1=request.getRequestDispatcher("forgot.html");
				rd1.include(request,response);
				out.write("<font color='OrangeRed'/>"+"Your email or phone Not match, Please Try again !!! ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}finally{
		JdbcUtil.cleanup(rs,con,ps);
		}
		}
	}

