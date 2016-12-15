package org.lip6.struts.servletAction;

import java.util.List;

import javassist.Loader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.lip6.struts.actionForm.AddContactValidationForm;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import domain.DAOContact;
import domain.DAOContactGroup;
import domain.Enterprise;
import domain.PhoneNumber;

public class NewContactAction extends Action{
	private static final String SUCCESS = "success";
	
	ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
	DAOContact lDAOContact = (DAOContact) context.getBean("DAOContact");
	DAOContactGroup lDAOContactGroup = (DAOContactGroup) context.getBean("DAOContactGroup");
	
	/**
	 * Create a contact / enterprise
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		AddContactValidationForm lForm = (AddContactValidationForm) form;
		
		String firstname = lForm.getFirstname();
		String lastname = lForm.getLastname();
		String email = lForm.getEmail();
		String street = lForm.getStreet();
		String city = lForm.getCity();
		String zip = lForm.getZip();
		String country = lForm.getCountry();
		String pHome = lForm.getHome();
		String pPerso = lForm.getPerso();
		String pWork = lForm.getWork();
		String family = lForm.getFamily();
		String friends = lForm.getFriends();
		String coworkers = lForm.getCoworkers();
		String enterprise = lForm.getEnterprise();
		String numSiret = lForm.getNumSiret();
		
		// create a new Contact
		Contact c = (Contact) context.getBean("Contact");
		c.setFirstname(firstname);
		c.setLastname(lastname);
		c.setEmail(email);
		
		Address a = (Address) context.getBean("Address");
		a.setStreet(street);
		a.setCity(city);
		a.setZip(zip);
		a.setCountry(country);
		
		Enterprise e = (Enterprise) context.getBean("Enterprise");
		e.setFirstname(firstname);
		e.setLastname(lastname);
		e.setEmail(email);
		e.setAdd(a);
		
		PhoneNumber pnH = (PhoneNumber) context.getBean("PhoneNumber");
		PhoneNumber pnP = (PhoneNumber) context.getBean("PhoneNumber");
		PhoneNumber pnW = (PhoneNumber) context.getBean("PhoneNumber");
		
		ContactGroup cgCoworkers = (ContactGroup) context.getBean("ContactGroup");
		ContactGroup cgFamily = (ContactGroup) context.getBean("ContactGroup");
		ContactGroup cgFriends = (ContactGroup) context.getBean("ContactGroup");
		
		
		if(!enterprise.equals("") && numSiret.length()>0){
			e.setNumSiret(Long.parseLong(numSiret));
			
			if(pHome != null && !pHome.equals("")){
				pnH.setPhoneKind("Home");
				pnH.setPhoneNumber(pHome);
				pnH.setContact(e);
				e.addPhone(pnH);
			}
			if(pPerso != null && !pPerso.equals("")){
				pnP.setPhoneKind("Perso");
				pnP.setPhoneNumber(pPerso);
				pnP.setContact(e);
				e.addPhone(pnP);
			}
			if(pWork != null && !pWork.equals("")){
				pnW.setPhoneKind("Work");
				pnW.setPhoneNumber(pWork);
				pnW.setContact(e);
				e.addPhone(pnW);
			}
			if(!family.equals("")){
				cgFamily.setGroupName("Family");
				cgFamily.addContactAuGroupe(e);
				
				e.addGroupe(cgFamily);
			}
			if(!friends.equals("")){
				cgFriends.setGroupName("Friends");
				cgFriends.addContactAuGroupe(e);
				
				e.addGroupe(cgFriends);
			}
			if(!coworkers.equals("")){
				cgCoworkers.setGroupName("Coworkers");
				cgCoworkers.addContactAuGroupe(e);
				
				e.addGroupe(cgCoworkers);
			}
			lDAOContact.createContact(e);
			
			if(!family.equals(""))
				lDAOContactGroup.createContactGroup(cgFamily);
			if(!friends.equals(""))
				lDAOContactGroup.createContactGroup(cgFriends);
			if(!coworkers.equals(""))
				lDAOContactGroup.createContactGroup(cgCoworkers);
			
		}else{
			c.setAdd(a);
			if(pHome != null && !pHome.equals("")){
				pnH.setPhoneKind("Home");
				pnH.setPhoneNumber(pHome);
				pnH.setContact(c);
				c.addPhone(pnH);
			}
			if(pPerso != null && !pPerso.equals("")){
				pnP.setPhoneKind("Perso");
				pnP.setPhoneNumber(pPerso);
				pnP.setContact(c);
				c.addPhone(pnP);
			}
			if(pWork != null && !pWork.equals("")){
				pnW.setPhoneKind("Work");
				pnW.setPhoneNumber(pWork);
				pnW.setContact(c);
				c.addPhone(pnW);
			}
			if(!family.equals("")){
				//ContactGroup cgFamily = new ContactGroup("family");
				cgFamily.setGroupName("Family");
				cgFamily.addContactAuGroupe(c);
				c.addGroupe(cgFamily);
			}
			if(!friends.equals("")){
				//ContactGroup cgFriends = new ContactGroup("friends");
				cgFriends.setGroupName("Friends");
				cgFriends.addContactAuGroupe(c);
				c.addGroupe(cgFriends);
			}
			if(!coworkers.equals("")){
				//ContactGroup cgCoworkers = new ContactGroup("coworkers");
				cgCoworkers.setGroupName("Coworkers");
				cgCoworkers.addContactAuGroupe(c);
				c.addGroupe(cgCoworkers);
			}
			
			lDAOContact.createContact(c);
			
			if(!family.equals(""))
				lDAOContactGroup.createContactGroup(cgFamily);
			if(!friends.equals(""))
				lDAOContactGroup.createContactGroup(cgFriends);
			if(!coworkers.equals(""))
				lDAOContactGroup.createContactGroup(cgCoworkers);
		}
		
		//request.setAttribute("msg", "Contact " + lForm.getFirstname() + " added succesfully !");
		
		return mapping.findForward(SUCCESS);
	}
	
}
