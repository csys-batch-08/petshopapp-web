
package com.petshopapp.controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/register")
public class Register extends HttpServlet {
	@Override
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		String firstName=req.getParameter("username");
		String LastName=req.getParameter("lastname");
		String gender=req.getParameter("gender");
		String userName=req.getParameter("username");
		String passweord=req.getParameter("password");
		String email=req.getParameter("email");
		long mobileNumber=Long.parseLong(req.getParameter("mobile"));	
		PrintWriter out=res.getWriter();
		CustomerDAO customerDao=new CustomerDAO();
		Customers customer=new Customers();
		customer.setFirstName(firstName);
		customer.setLastName(LastName);
		customer.setGender(gender);
		customer.setUserName(userName);
		customer.setPassword(passweord);
		customer.setEmail(email);
		customer.setNumber(mobileNumber);
		boolean register=customerDao.insertNewCustomer(customer);
		
			if(register) {
	            out.print("<script type=\"text/javascript\">alert('registration completed successfully login now');"
	            		+ "window.location = 'index.jsp';</script>");
			}
			else {
				 out.print("<script type=\"text/javascript\">alert('Something went to wrong try again');"
		            		+ "window.location = 'register.jsp';</script>");

			}
}
}