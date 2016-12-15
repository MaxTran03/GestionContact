package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Contact;
import domain.DAOContact;

/**
 * Servlet implementation class searchModels
 */
public class searchModels extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searchModels() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fn = request.getParameter("param");
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		
		if(request.getParameter("param") != null){
			DAOContact dc = new DAOContact();
			
			Contact contact = new Contact();
			if(!lastname.equals(""))
				contact.setLastname(lastname);
			if(!firstname.equals(""))
				contact.setFirstname(firstname);
			if(!email.equals(""))
				contact.setEmail(email);
				
				
			List<Contact> list = dc.searchContactByModels(contact);
			RequestDispatcher rd = request.getRequestDispatcher("searchModels.jsp");
			request.setAttribute("recherche", list);
			rd.forward(request, response);
		}else if(fn.equals("")){
			RequestDispatcher rd = request.getRequestDispatcher("searchModels.jsp");
			rd.forward(request, response);
		}
	}

}