package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDTO;
import model.WebDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		WebDAO dao = new WebDAO();
		HttpSession session = request.getSession();


		try {
			boolean ischecked = dao.Login(email, pw);

			if (ischecked) {
				MemberDTO tel = dao.session(email);
				session.setAttribute("address", tel.getAddr());
				session.setAttribute("email", email);
				session.setAttribute("tel", tel.getTel());
				response.sendRedirect("main.jsp");
			} else {
				System.err.println("로그인실패");
				response.sendRedirect("main.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
