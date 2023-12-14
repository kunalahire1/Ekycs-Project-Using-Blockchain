package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;





@WebServlet("/UserOtpController")
public class UserOtpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserOtpController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
System.out.println("hiiii");
		
     PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String email1=(String)session.getAttribute("email");
		System.out.println(email1);
		String genotp = request.getParameter("genotp");
		System.out.println(genotp);
		StudentDao userDao=new StudentDao();
		String sql1 = "SELECT MAX(id) FROM userlogin where  email ='"+email1+"' ";
		System.out.println(sql1);
		String genotp1 = userDao.selectotp(sql1);
		String sql2 = "SELECT otp FROM userlogin where  id ='"+genotp1+"' ";
		String genotp2 = userDao.selectotp1(sql2);
		System.out.println(genotp2);
		if(genotp.equals(genotp2)  ){
	
			 out.println("<script type=\"text/javascript\">");
		       out.println("alert('Login Success');");
		       out.println("location='StudentHome.jsp';");
		       out.println("</script>");
		}
		else{
			request.setAttribute("sucmsg", "Invalid otp");
			 out.println("<script type=\"text/javascript\">");
		       out.println("alert('Failed Otp');");
		       out.println("location='UserOTP.jsp';");
		       out.println("</script>");
			
		}
	}

}
