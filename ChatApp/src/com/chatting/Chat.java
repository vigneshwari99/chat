package com.chatting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/chat")
public class Chat extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			String email = request.getParameter("uname");
			String pass = request.getParameter("pw");
			Class.forName("org.postgresql.Driver");
		Connection	con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee_Attrition","postgres","admin");
			Statement st = con.createStatement();
			String q = "select * from registeruser where fname= '"+email+"' and pass ='"+pass+"' ";
		
		ResultSet rs = st.executeQuery(q);
		if(rs.next()){
			String user= rs.getString("fname");
			HttpSession hs = request.getSession();
			hs.setAttribute("n",user);
			out.println("welcome,"+email.toUpperCase());
			out.print("<a href='startchat.jsp'>chatroom</a>");
			out.print("<a href='logout.jsp' class='logout-chat'>logout</a>");
		}
		else{
			out.println("failure");
		}
		con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
