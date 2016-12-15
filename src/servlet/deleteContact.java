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
 * Servlet implementation class deleteContact
 */
public class deleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteContact() {
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
		String idContact = request.getParameter("idContact");
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		// On soumet une recherche
		if(request.getParameter("recherche")!=null){
			DAOContact dc = (DAOContact) context.getBean("DAOContact");
			List<Contact> c = dc.searchContact(fn);

			RequestDispatcher rd = request.getRequestDispatcher("removeContact.jsp");

			request.setAttribute("recherche", c);
			rd.forward(request, response);
		}
		
		// On soumet une suppression
		else if(request.getParameter("suppression")!=null && !idContact.equals("")){
			DAOContact dc = (DAOContact) context.getBean("DAOContact");
			dc.deleteContact(Long.parseLong(idContact));

			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}
		
		// Si les champs sont vides, on revient à la même page
		else if(fn.equals("") || idContact.equals("")){
			RequestDispatcher rd = request.getRequestDispatcher("removeContact.jsp");
			rd.forward(request, response);
		}
		
	}

}
