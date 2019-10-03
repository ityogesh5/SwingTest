package com.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Connection con;
		Statement st;
		ResultSet rs;
		PrintWriter out = response.getWriter();
		String gi = request.getParameter("hdnbt");
		// System.out.println(gi);
		try {
			int count = 0;
			String uname = request.getParameter("username");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nexa_db", "root", "");
			st = con.createStatement();
			if (gi.equals("Register")) {
				st.executeUpdate("insert into register values('" + uname + "','" 
			+ email + "'," + "'" + phone + "')");
				st = con.createStatement();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Registered Successfully');");
				out.println("location='index.html';");
				out.println("</script>");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("SelectServlet");
				dispatcher.forward(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			out.print(e);

		}

	}

}
