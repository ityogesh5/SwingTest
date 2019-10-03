package com.jdbc.servlet;

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

@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Connection con;
		Statement st;
		ResultSet rs;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nexa_db", "root", "");
			st = con.createStatement();
			rs = st.executeQuery("select * from register");
			out.println("<center><table border=1 width=70%>");
			out.println("<th>Uname</th><th>Email</th><th>Phone no</th>");
			while (rs.next()) {
				out.print("<tr>");
				out.print("<td>" + rs.getString("username") + "</td><td>" 
				+ rs.getString("email") + "</td><td>"
						+ rs.getString("phone") + "</td></br>");
				out.print("</tr>");

			}
			out.print("</table></center>");
			con.close();
		} catch (Exception e) {
			out.println("Error Occured : " + e);

		}

	}

}
