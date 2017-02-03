package com.roy;
import Util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
public class PartnerServlet extends HttpServlet {
	 PreparedStatement ps= null;
	 Connection con = null;
	String name;
	String email;
	String mobileno;
	String msg;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		name = request.getParameter("name");
		email = request.getParameter("email");
		mobileno=request.getParameter("phone");
		msg=request.getParameter("message");
		//if(name!=null && email!=null && mobileno.length()!=10 && position!=null && resume!=null){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/roydb";
			con=DriverManager.getConnection(url,"root","FgYuy*(oiTR");
			String sql= "insert into partnership_reg values(?,?,?,?)";
			ps= con.prepareStatement(sql);
			ps.setString(1, name);
		    ps.setString(2,mobileno);
			ps.setString(3,email);
			ps.setString(4,msg);
		     ps.executeUpdate();
	out.write("Thank you \t"+name+""+"\t,we will respone you soon !!!"+"<a href='index.html'><font color='OrangeRe'>Go To Home</a>");
    }
		 catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		JdbcUtil.cleanup(ps,con);
		}
	}
}


