package com.chatting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/chatstore")
public class ChatStore extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();			
			
			Class.forName("org.postgresql.Driver");
		Connection	con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee_Attrition","postgres","admin");
			Statement st = con.createStatement(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
			Date date = new Date(); 
			String cmtDate = sdf.format(date);
			String cmttime = sdf2.format(date);
			String uname = request.getParameter("uname");
			String msg = request.getParameter("msg");
			System.out.println(uname);
			System.out.println(msg);
		String q = "insert into chat_detail values('"+uname+"','"+msg+"','"+cmtDate+"','"+cmttime+"')";
		ResultSet rs = st.executeQuery(q);
		rs.next();
			con.close();	
		}	
		
		catch(Exception e){
			System.out.println(e);
		}
	}
}
