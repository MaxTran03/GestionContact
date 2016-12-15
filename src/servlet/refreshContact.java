package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import domain.DAOContact;
import domain.DAOContactGroup;
import domain.DAOEnterprise;
import domain.DAOPhoneNumber;
import domain.Enterprise;
import domain.PhoneNumber;

/**
 * Servlet implementation class refreshContact
 */
public class refreshContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public refreshContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**ct.java:60)
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String fn = request.getParameter("firstname");
		String idContact = request.getParameter("idContact");
		String idPhoneHome = request.getParameter("idphonehome");
		String idPhoneWork = request.getParameter("idphonework");
		String idPhonePerso = request.getParameter("idphoneperso");
		String idGroupFamily = request.getParameter("idgroupfamily");
		String idGroupFriends = request.getParameter("idgroupfriends");
		String idGroupCoworkers = request.getParameter("idgroupcoworkers");

		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		// Chercher le contact à modifier
		if(request.getParameter("recherche") != null){
			DAOContact dc = (DAOContact) context.getBean("DAOContact");
			List<Contact> contacts = dc.searchContact(fn);

			RequestDispatcher rd = request.getRequestDispatcher("updateContact.jsp");

			request.setAttribute("recherche", contacts);
			rd.forward(request, response);
		}

		// ID du contact à modifier
		else if(request.getParameter("modification") != null && !idContact.equals("")){
			DAOContact dc = (DAOContact) context.getBean("DAOContact");
			Contact c = dc.searchContactByID(Long.parseLong(idContact));

			request.setAttribute("modification", c);
			request.setAttribute("isEntreprise", c instanceof Enterprise);

			RequestDispatcher rd = request.getRequestDispatcher("updateContact.jsp");
			rd.forward(request, response);
		}

		// Valider les modifications
		else if(request.getParameter("validation") != null){
			String id = request.getParameter("ideo");

			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");

			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String zip = request.getParameter("zip");
			String country = request.getParameter("country");

			String homeNum = request.getParameter("Home");
			String persoNum = request.getParameter("Perso");
			String workNum = request.getParameter("Work");

			String groupNameFamily = request.getParameter("family");
			String groupNameFriends = request.getParameter("friends");
			String groupNameCoworkers = request.getParameter("coworkers");

			String cbox = request.getParameter("cbox");
			String numSiret = request.getParameter("numSiret");

			DAOContact dc = (DAOContact) context.getBean("DAOContact");
			DAOContactGroup dcg = (DAOContactGroup) context.getBean("DAOContactGroup");
			DAOPhoneNumber dn = (DAOPhoneNumber) context.getBean("DAOPhoneNumber");
			//DAOEnterprise de = (DAOEnterprise) context.getBean("DAOEnterprise");
			
			Contact c = dc.searchContactByID(Long.parseLong(id));
			Enterprise e = dc.searchEnterpriseByID(Long.parseLong(id));
			
			Address address = (Address) context.getBean("Address");
			address.setStreet(street);
			address.setCity(city);
			address.setZip(zip);
			address.setCountry(country);
			
			ContactGroup cgFamily = null;
			ContactGroup cgFriends = null;
			ContactGroup cgCoworkers = null;
			
			PhoneNumber pnH = (PhoneNumber) context.getBean("PhoneNumber");
			PhoneNumber pnP = (PhoneNumber) context.getBean("PhoneNumber");
			PhoneNumber pnW = (PhoneNumber) context.getBean("PhoneNumber");


			if(cbox.equals(new String("entreprise"))){
				Enterprise enterpriseModifie = (Enterprise) context.getBean("Enterprise");

				enterpriseModifie.setFirstname(firstname);
				enterpriseModifie.setLastname(lastname);
				enterpriseModifie.setEmail(email);
				enterpriseModifie.setAdd(address);
				enterpriseModifie.setNumSiret(Long.parseLong(numSiret));
				
				ContactGroup cgtest = null;
				ContactGroup cgtest2 = null;
				ContactGroup cgtest3 = null;
				Set<ContactGroup> set2 = enterpriseModifie.getBooks();
				Iterator<ContactGroup> it2 = set2.iterator(); 
				
				if (it2.hasNext())
				 	cgtest = it2.next();
				if (it2.hasNext())
				 	cgtest2 = it2.next();
				if (it2.hasNext())
				 	cgtest3 = it2.next();
				
				if(cgtest != null && cgtest.getGroupName().equals("family"))
					cgFamily = cgtest;
				if(cgtest != null && cgtest.getGroupName().equals("friends"))
					cgFriends = cgtest;
				if(cgtest != null && cgtest.getGroupName().equals("coworkers"))
					cgCoworkers = cgtest;
				
				if(cgtest2 != null && cgtest2.getGroupName().equals("family"))
					cgFamily = cgtest2;
				if(cgtest2 != null && cgtest2.getGroupName().equals("friends"))
					cgFriends = cgtest2;
				if(cgtest2 != null && cgtest2.getGroupName().equals("coworkers"))
					cgCoworkers = cgtest2;
				
				if(cgtest3 != null && cgtest3.getGroupName().equals("family"))
					cgFamily = cgtest3;
				if(cgtest3 != null && cgtest3.getGroupName().equals("friends"))
					cgFriends = cgtest3;
				if(cgtest3 != null && cgtest3.getGroupName().equals("coworkers"))
					cgCoworkers = cgtest3;
				
				enterpriseModifie.getBooks().clear();
				enterpriseModifie.getPhones().clear();
				
				/*
				try{
					cgFamily = (ContactGroup) session.load(ContactGroup.class, Long.parseLong(idGroupFamily));
				}catch(Exception e1){}
				
				try{
					cgFriends = (ContactGroup) session.load(ContactGroup.class, Long.parseLong(idGroupFriends));
				}catch(Exception e1){}
				
				try{
					cgCoworkers = (ContactGroup) session.load(ContactGroup.class, Long.parseLong(idGroupCoworkers));
				}catch(Exception e1){}*/
				

				
				if(groupNameFamily!=null){
					if(cgFamily==null){
						cgFamily = new ContactGroup("family");
						enterpriseModifie.addGroupe(cgFamily);
						cgFamily.addContactAuGroupe(enterpriseModifie);
						dcg.createContactGroup(cgFamily);
					}else{
						enterpriseModifie.addGroupe(cgFamily);
					}
				}else if(groupNameFamily==null){
					if(cgFamily!=null){
						enterpriseModifie.getBooks().remove(cgFamily);
						cgFamily.removeContact(enterpriseModifie);
						dcg.deleteContactGroupe(cgFamily);
					}
				}
				
				if(groupNameFriends!=null){
					if(cgFriends==null){
						cgFriends = new ContactGroup("friends");
						enterpriseModifie.addGroupe(cgFriends);
						cgFriends.addContactAuGroupe(enterpriseModifie);
						dcg.createContactGroup(cgFriends);
					}else{
						enterpriseModifie.addGroupe(cgFriends);
					}
				}else if(groupNameFriends==null){
					if(cgFriends!=null){
						enterpriseModifie.getBooks().remove(cgFriends);
						cgFriends.removeContact(enterpriseModifie);
						dcg.deleteContactGroupe(cgFriends);
					}
				}
				
				if(groupNameCoworkers!=null){
					if(cgCoworkers==null){
						cgCoworkers = new ContactGroup("coworkers");
						enterpriseModifie.addGroupe(cgCoworkers);
						cgCoworkers.addContactAuGroupe(enterpriseModifie);
						dcg.createContactGroup(cgCoworkers);
					}else{
						enterpriseModifie.addGroupe(cgCoworkers);
					}
				}else if(groupNameCoworkers==null){
					if(cgCoworkers!=null){
						enterpriseModifie.getBooks().remove(cgCoworkers);
						cgCoworkers.removeContact(enterpriseModifie);
						dcg.deleteContactGroupe(cgCoworkers);
						//dcg.updateContactGroup(cgCoworkers);
					}
				}
				
				System.out.println("====== |" + idPhoneHome + "|====== |" + idPhonePerso + "|====== |" + idPhoneWork);
				
				if(idPhoneHome != null && !idPhoneHome.equals(""))
					dn.deletePhoneNumber(Long.parseLong(idPhoneHome));
				if(idPhoneWork != null && !idPhoneWork.equals(""))
					dn.deletePhoneNumber(Long.parseLong(idPhoneWork));
				if(idPhonePerso !=null && !idPhonePerso.equals(""))
					dn.deletePhoneNumber(Long.parseLong(idPhonePerso));		
				
				
				if(homeNum!=""){
					PhoneNumber pnHome = new PhoneNumber("Home", homeNum,e);
					enterpriseModifie.addPhone(pnHome);
					pnHome.setContact(enterpriseModifie);
					dn.createPhoneNumber(pnHome);
				}
				
				if(workNum!=""){
					PhoneNumber pnWork = new PhoneNumber("Work", workNum,e);
					enterpriseModifie.addPhone(pnWork);
					pnWork.setContact(enterpriseModifie);
					dn.createPhoneNumber(pnWork);
				}
				
				if(persoNum != ""){
					PhoneNumber pnPerso = new PhoneNumber("Perso", persoNum, e);
					enterpriseModifie.addPhone(pnPerso);
					pnPerso.setContact(enterpriseModifie);
					dn.createPhoneNumber(pnPerso);
				}

				dc.updateContact(Long.parseLong(id), enterpriseModifie);

				RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
				rd.forward(request, response);
			}else{

				Contact contactModifie = (Contact) context.getBean("Contact");
				contactModifie.setFirstname(firstname);
				contactModifie.setLastname(lastname);
				contactModifie.setEmail(email);
				contactModifie.setAdd(address);
				contactModifie.getBooks().clear();
				contactModifie.getPhones().clear();
				
				dn.searchPhoneNumber();
				dn.searchPhoneNumberByContactID(Long.parseLong(id));
				
				ContactGroup cgtest = null;
				ContactGroup cgtest2 = null;
				ContactGroup cgtest3 = null;
				Set<ContactGroup> set2 = contactModifie.getBooks();
				Iterator<ContactGroup> it2 = set2.iterator(); 
				
				if (it2.hasNext())
				 	cgtest = it2.next();
				if (it2.hasNext())
				 	cgtest2 = it2.next();
				if (it2.hasNext())
				 	cgtest3 = it2.next();
				
				if(cgtest.getGroupName().equals("family"))
					cgFamily = cgtest;
				if(cgtest.getGroupName().equals("friends"))
					cgFriends = cgtest;
				if(cgtest.getGroupName().equals("coworkers"))
					cgCoworkers = cgtest;
				
				if(cgtest2.getGroupName().equals("family"))
					cgFamily = cgtest2;
				if(cgtest2.getGroupName().equals("friends"))
					cgFriends = cgtest2;
				if(cgtest2.getGroupName().equals("coworkers"))
					cgCoworkers = cgtest2;
				
				if(cgtest3.getGroupName().equals("family"))
					cgFamily = cgtest3;
				if(cgtest3.getGroupName().equals("friends"))
					cgFriends = cgtest3;
				if(cgtest3.getGroupName().equals("coworkers"))
					cgCoworkers = cgtest3;
				
				/*
				try{
					cgFamily = (ContactGroup) session.load(ContactGroup.class, Long.parseLong(idGroupFamily));
				}catch(Exception e1){}
				
				try{
					cgFriends = (ContactGroup) session.load(ContactGroup.class, Long.parseLong(idGroupFriends));
				}catch(Exception e1){}
				
				try{
					cgCoworkers = (ContactGroup) session.load(ContactGroup.class, Long.parseLong(idGroupCoworkers));
				}catch(Exception e1){}
				*/
				
				if(groupNameFamily!=null){
					if(cgFamily==null){
						cgFamily = new ContactGroup("family");
						contactModifie.addGroupe(cgFamily);
						cgFamily.addContactAuGroupe(contactModifie);
						dcg.createContactGroup(cgFamily);
					}else{
						contactModifie.addGroupe(cgFamily);
					}
				}else if(groupNameFamily==null){
					if(cgFamily!=null){
						contactModifie.getBooks().remove(cgFamily);
						cgFamily.removeContact(contactModifie);
						dcg.deleteContactGroupe(cgFamily);
					}
				}
				
				if(groupNameFriends!=null){
					if(cgFriends==null){
						cgFriends = new ContactGroup("friends");
						contactModifie.addGroupe(cgFriends);
						cgFriends.addContactAuGroupe(contactModifie);
						dcg.createContactGroup(cgFriends);
					}else{
						contactModifie.addGroupe(cgFriends);
					}
				}else if(groupNameFriends==null){
					if(cgFriends!=null){
						contactModifie.getBooks().remove(cgFriends);
						cgFriends.removeContact(contactModifie);
						dcg.deleteContactGroupe(cgFriends);
					}
				}
				
				if(groupNameCoworkers!=null){
					if(cgCoworkers==null){
						cgCoworkers = new ContactGroup("coworkers");
						contactModifie.addGroupe(cgCoworkers);
						cgCoworkers.addContactAuGroupe(contactModifie);
						dcg.createContactGroup(cgCoworkers);
					}else{
						contactModifie.addGroupe(cgCoworkers);
					}
				}else if(groupNameCoworkers==null){
					if(cgCoworkers!=null){
						contactModifie.getBooks().remove(cgCoworkers);
						cgCoworkers.removeContact(contactModifie);
						dcg.deleteContactGroupe(cgCoworkers);
//						dcg.updateContactGroup(cgCoworkers);
					}
				}

				if(idPhoneHome != null)
					dn.deletePhoneNumber(Long.parseLong(idPhoneHome));
				if(idPhoneWork != null)
					dn.deletePhoneNumber(Long.parseLong(idPhoneWork));
				if(idPhonePerso !=null)
					dn.deletePhoneNumber(Long.parseLong(idPhonePerso));							
					
				if(homeNum!=""){
					PhoneNumber pnHome = new PhoneNumber("Home", homeNum,c);
					contactModifie.addPhone(pnHome);
					pnHome.setContact(contactModifie);
					dn.createPhoneNumber(pnHome);
				}
				
				if(workNum!=""){
					PhoneNumber pnWork = new PhoneNumber("Work", workNum,c);
					contactModifie.addPhone(pnWork);
					pnWork.setContact(contactModifie);
					dn.createPhoneNumber(pnWork);
				}
				
				if(persoNum != ""){
					PhoneNumber pnPerso = new PhoneNumber("Perso", persoNum, c);
					contactModifie.addPhone(pnPerso);
					pnPerso.setContact(contactModifie);
					dn.createPhoneNumber(pnPerso);
				}
				

				dc.updateContact(Long.parseLong(id), contactModifie);

				RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
				rd.forward(request, response);
			}

		}

		// Si les champs sont vides, on revient à la même page
		else if(fn.equals("") || idContact.equals("")){
			RequestDispatcher rd = request.getRequestDispatcher("updateContact.jsp");
			rd.forward(request, response);
		}
	}

}
