package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import dao.Mydao;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/signup")
@MultipartConfig
public class Singup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Long mobile =Long.parseLong( req.getParameter("mobile"));
		LocalDate date = LocalDate.parse(req.getParameter("date"));
		String gender = req.getParameter("gender");
		int age = Period.between(date, LocalDate.now()).getYears();
		Part pic = req.getPart("picture");
		byte picture[] = null;
		picture  = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		Mydao dao = new Mydao();
		if(dao.fetchByEmail(email)==null && dao.fetchByMobile(mobile)==null) {
		
		Customer c = new Customer();
		c.setAge(age);
		c.setDob(date);
		c.setEmail(email);
		c.setGender(gender);
		c.setMobile(mobile);
		c.setName(name);
		c.setPassword(password);
		c.setPicture(picture);
		
		
		dao.save(c);
		
		
		resp.getWriter().print("<h1 style = 'color : brown'>Account Created Successfully</h1>");
		
	}else {
		resp.getWriter().print("<h1 style='color:red'>Email and Mobile should be Unique</h1>");

	}
		
	
	
}
}