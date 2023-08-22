package Controller;

import java.io.IOException;

import dao.Mydao;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// mapping the Url
@WebServlet("/login")
public class login extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	Receive values from front-end
	String email = req.getParameter("email");
	String password = req.getParameter("password");
//	verify if email exists
	Mydao dao=new Mydao();
	Customer customer=dao.fetchByEmail(email);
	if(customer==null) {
		resp.getWriter().print("<h1 style='color:red'>Invalid Email</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
	}
	else {
		if(password.equals(customer.getPassword())) {
			resp.getWriter().print("<h1 style='color:pink'>Login Success</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);

		}else {
			if(password.equals(customer.getPassword())) {
				resp.getWriter().print("<h1 style='color:pink'>Invalid password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);

			}
	}
	
}
}
}
