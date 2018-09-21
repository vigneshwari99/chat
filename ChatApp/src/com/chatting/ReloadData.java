package com.chatting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/reloaddata")
public class ReloadData extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("org.postgresql.Driver");
		Connection	con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee_Attrition","postgres","admin");
		String q = "select * from chat_detail";
		PreparedStatement st = con.prepareStatement(q);
			
		
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			String user= rs.getString(1);
			String msg= rs.getString(2);
			String mdate= rs.getString(3);
			String mtime = rs.getString(4);
			out.print("<p>" +user+"<g>"+msg+"</g><br><small>"+mdate+""+mtime+"</small></p>");
			
		}
		
		con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	doGet(request,response);
}
}
