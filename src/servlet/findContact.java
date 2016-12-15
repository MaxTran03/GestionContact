package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Contact;
import domain.DAOContact;

/**
 * Servlet implementation class findContact
 */
public class findContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public findContact() {
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
		String fn = request.getParameter("firstname");
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		// Chercher le contact à modifier
		if(request.getParameter("recherche") != null){
			DAOContact dc = (DAOContact) context.getBean("DAOContact");
			List<Contact> contacts = dc.searchContact(fn);

			RequestDispatcher rd = request.getRequestDispatcher("searchContact.jsp");

			request.setAttribute("recherche", contacts);
			rd.forward(request, response);
		}else if(fn.equals("")){
			RequestDispatcher rd = request.getRequestDispatcher("searchContact.jsp");
			rd.forward(request, response);
		}
	}

}
