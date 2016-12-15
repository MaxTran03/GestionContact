package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import domain.DAOAddress;
import domain.DAOContact;
import domain.DAOContactGroup;
import domain.DAOEnterprise;
import domain.DAOPhoneNumber;
import domain.Enterprise;
import domain.PhoneNumber;

/**
 * Servlet implementation class newContact
 */
public class newContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newContact() {
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
		
		// Information sur la personne
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		// Information sur l'adresse
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		
		// Information sur le téléphone
		String phoneKind = request.getParameter("phoneKind");
		String phoneNumber = request.getParameter("phoneNumber");
		
		// Information sur le groupe
		String groupName = request.getParameter("groupName");
		
		// Information sur l'entreprise
		String cbox = request.getParameter("cbox");
		String numSiret = request.getParameter("numSiret");
		
		
		/*
		DAOContact dc = new DAOContact();
		DAOContactGroup dcg = new DAOContactGroup();
		DAOAddress da = new DAOAddress();
		DAOEnterprise de = new DAOEnterprise();
		DAOPhoneNumber dpn = new DAOPhoneNumber();
		*/
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		DAOContact dc = (DAOContact) context.getBean("DAOContact");
		DAOContactGroup dcg = (DAOContactGroup) context.getBean("DAOContactGroup");
		DAOAddress da = (DAOAddress) context.getBean("DAOAddress");
		DAOEnterprise de = (DAOEnterprise) context.getBean("DAOEnterprise");
		DAOPhoneNumber dpn = (DAOPhoneNumber) context.getBean("DAOPhoneNumber");
		
		if(cbox.equals(new String("entreprise"))){
			
			Address address = (Address) context.getBean("Address");
			address.setCity(city);
			address.setCountry(country);
			address.setStreet(street);
			address.setZip(zip);
			
			Enterprise e = (Enterprise) context.getBean("Enterprise");
			e.setEmail(email);
			e.setFirstname(firstname);
			e.setLastname(lastname);
			e.setNumSiret(Long.parseLong(numSiret));
			e.setAdd(address);
			
			ContactGroup cg = (ContactGroup) context.getBean("ContactGroup");
			cg.setGroupName(groupName);
			
			PhoneNumber pn = (PhoneNumber) context.getBean("PhoneNumber");
			pn.setContact(e);
			pn.setPhoneKind(phoneKind);
			pn.setPhoneNumber(phoneNumber);
			
			/*Address address = new Address(street, city, zip, country);
			Enterprise e = new Enterprise(firstname, lastname, email, address, Long.parseLong(numSiret));
			ContactGroup cg = new ContactGroup(groupName);
			PhoneNumber pn = new PhoneNumber(phoneKind, phoneNumber);*/
			
			cg.addContactAuGroupe(e);
			e.addGr(cg);
			e.addPhone(pn);
			pn.setContact(e);
			
			da.createAddress(address);
			de.createEnterprise(e);
			dcg.createContactGroup(cg);
			
		}else{
			
			/*Address address = new Address(street, city, zip, country);
			Contact c = new Contact(firstname, lastname, email, address);
			ContactGroup cg = new ContactGroup(groupName);
			PhoneNumber pn = new PhoneNumber(phoneKind, phoneNumber, c);*/
			
			Address address = (Address) context.getBean("Address");
			address.setCity(city);
			address.setCountry(country);
			address.setStreet(street);
			address.setZip(zip);
			
			Contact c = (Contact) context.getBean("Contact");
			c.setEmail(email);
			c.setFirstname(firstname);
			c.setLastname(lastname);
			c.setAdd(address);
			
			ContactGroup cg = (ContactGroup) context.getBean("ContactGroup");
			cg.setGroupName(groupName);
			
			PhoneNumber pn = (PhoneNumber) context.getBean("PhoneNumber");
			pn.setContact(c);
			pn.setPhoneKind(phoneKind);
			pn.setPhoneNumber(phoneNumber);
			
			cg.addContactAuGroupe(c);
			c.addGr(cg);
			c.addPhone(pn);
			
			da.createAddress(address);
			dc.createContact(c);
			dcg.createContactGroup(cg);
		}

		
		RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
		rd.forward(request, response);
		
		
	}

}
