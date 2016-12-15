package org.lip6.struts.servletAction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.lip6.struts.actionForm.UpdateContactValidationForm;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import domain.DAOContact;
import domain.DAOContactGroup;
import domain.DAOPhoneNumber;
import domain.Enterprise;
import domain.PhoneNumber;

public class UpdateContactAction extends Action{

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest, final HttpServletResponse pResponse){
		UpdateContactValidationForm lForm = (UpdateContactValidationForm) pForm;
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		
		// Contact
		String firstname = lForm.getFirstname();
		String lastname = lForm.getLastname();
		String email = lForm.getEmail();
		
		// Address
		String street = lForm.getStreet();
		String city = lForm.getCity();
		String zip = lForm.getZip();
		String country = lForm.getCountry();
		
		// PhoneNumber
		String homeNum = lForm.getHome();
		String persoNum = lForm.getPerso();
		String workNum = lForm.getWork();
		
		// ContactGroup
		String groupNameFamily = lForm.getFamily();
		String groupNameFriends = lForm.getFriends();
		String groupNameCoworkers = lForm.getCoworkers();
		
		// Enterprise
		String enterprise = lForm.getCbox();
		String numSiret = lForm.getNumSiret();
		
		// ID ContactGroup & PhoneNumber
		String idContact = lForm.getIdeo();
		String idPhoneHome = lForm.getIdphonehome();
		String idPhoneWork = lForm.getIdphonework();
		String idPhonePerso = lForm.getIdphoneperso();
		String idGroupFamily = lForm.getIdgroupfamily();
		String idGroupFriends = lForm.getIdgroupfriends();
		String idGroupCoworkers = lForm.getIdgroupcoworkers();
		
		DAOContact dc = (DAOContact) context.getBean("DAOContact");
		DAOContactGroup dcg = (DAOContactGroup) context.getBean("DAOContactGroup");
		DAOPhoneNumber dn = (DAOPhoneNumber) context.getBean("DAOPhoneNumber");
		
		Contact c = dc.searchContactByID(Long.parseLong(idContact));
		Enterprise e = dc.searchEnterpriseByID(Long.parseLong(idContact));
		
		Address address = (Address) context.getBean("Address");
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		
		ContactGroup cgFamily = null;
		ContactGroup cgFriends = null;
		ContactGroup cgCoworkers = null;
		
		PhoneNumber pnHome = (PhoneNumber) context.getBean("PhoneNumber");
		PhoneNumber pnPerso = (PhoneNumber) context.getBean("PhoneNumber");
		PhoneNumber pnWork = (PhoneNumber) context.getBean("PhoneNumber");
		
		if(enterprise.equals(new String("entreprise"))){
			/*************************** ENTERPRISE ***************************/
			
			Enterprise enterpriseModifie = dc.loadEnterprise(Long.parseLong(idContact));
			
			enterpriseModifie.setFirstname(firstname);
			enterpriseModifie.setLastname(lastname);
			enterpriseModifie.setEmail(email);
			enterpriseModifie.setAdd(address);
			enterpriseModifie.setNumSiret(Long.parseLong(numSiret));
			
			/*
			ContactGroup cgtest = null;
			Set<ContactGroup> set2 = enterpriseModifie.getBooks();
			Iterator<ContactGroup> it2 = set2.iterator(); 
			
			while (it2.hasNext()){
		 		cgtest = it2.next();
			}*/
			
			try{
				cgFamily = dcg.loadContactGroup(Long.parseLong(idGroupFamily));
			}catch(Exception e1){}
			
			try{
				cgFriends = dcg.loadContactGroup(Long.parseLong(idGroupFriends));
			}catch(Exception e1){}
			
			try{
				cgCoworkers = dcg.loadContactGroup(Long.parseLong(idGroupCoworkers));
			}catch(Exception e1){}
			
			/*
			enterpriseModifie.getBooks().clear();
			enterpriseModifie.getPhones().clear();*/
			
			// Si group Family est coché
			if(!groupNameFamily.equals("")){
				if(cgFamily==null){
					cgFamily = (ContactGroup) context.getBean("ContactGroup");
					cgFamily.setGroupName("Family");
					enterpriseModifie.addGroupe(cgFamily);
					cgFamily.addContactAuGroupe(enterpriseModifie);
					dcg.createContactGroup(cgFamily);
				}else{
					enterpriseModifie.addGroupe(cgFamily);
				}
				// Sinon ce n'est pas coché
			}else if(groupNameFamily.equals("")){
				if(cgFamily!=null){
					enterpriseModifie.getBooks().remove(cgFamily);
					cgFamily.removeContact(enterpriseModifie);
					dcg.updateContactGroup(cgFamily);
				}
			}
			
			// Si group Friends est coché
			if(!groupNameFriends.equals("")){
				if(cgFriends==null){
					cgFriends = (ContactGroup) context.getBean("ContactGroup");
					cgFriends.setGroupName("Friends");
					enterpriseModifie.addGroupe(cgFriends);
					cgFriends.addContactAuGroupe(enterpriseModifie);
					dcg.createContactGroup(cgFriends);
				}else{
					enterpriseModifie.addGroupe(cgFriends);
				}
				// Sinon ce n'est pas coché
			}else if(groupNameFriends.equals("")){
				if(cgFriends!=null){
					enterpriseModifie.getBooks().remove(cgFriends);
					cgFriends.removeContact(enterpriseModifie);
					dcg.updateContactGroup(cgFriends);
				}
			}
			
			// Si group Coworkers est coché
			if(!groupNameCoworkers.equals("")){
				if(cgCoworkers==null){
					cgCoworkers = (ContactGroup) context.getBean("ContactGroup");
					cgCoworkers.setGroupName("Coworkers");
					enterpriseModifie.addGroupe(cgCoworkers);
					cgCoworkers.addContactAuGroupe(enterpriseModifie);
					dcg.createContactGroup(cgCoworkers);
				}else{
					enterpriseModifie.addGroupe(cgCoworkers);
				}
				// Sinon ce n'est pas coché
			}else if(groupNameCoworkers.equals("")){
				if(cgCoworkers!=null){
					enterpriseModifie.getBooks().remove(cgCoworkers);
					cgCoworkers.removeContact(enterpriseModifie);
					dcg.updateContactGroup(cgCoworkers);
				}
			}
			
			//System.out.println("====== |" + idPhoneHome + "|====== |" + idPhonePerso + "|====== |" + idPhoneWork);
			
			if(idPhoneHome != null && !idPhoneHome.equals(""))
				dn.deletePhoneNumber(Long.parseLong(idPhoneHome));
			if(idPhoneWork != null && !idPhoneWork.equals(""))
				dn.deletePhoneNumber(Long.parseLong(idPhoneWork));
			if(idPhonePerso !=null && !idPhonePerso.equals(""))
				dn.deletePhoneNumber(Long.parseLong(idPhonePerso));	
			
			// Ajout du num
			if(homeNum != null && homeNum!=""){
				pnHome.setPhoneKind("Home");
				pnHome.setPhoneNumber(homeNum);
				pnHome.setContact(enterpriseModifie);
				
				enterpriseModifie.addPhone(pnHome);
				dn.createPhoneNumber(pnHome);
			}
			
			if(workNum != null && workNum!=""){
				pnWork.setPhoneKind("Work");
				pnWork.setPhoneNumber(workNum);
				pnWork.setContact(enterpriseModifie);
				
				enterpriseModifie.addPhone(pnWork);
				dn.createPhoneNumber(pnWork);
			}
			
			if(persoNum != null && persoNum != ""){
				pnPerso.setPhoneKind("Perso");
				pnPerso.setPhoneNumber(persoNum);
				pnPerso.setContact(enterpriseModifie);
				
				enterpriseModifie.addPhone(pnPerso);
				dn.createPhoneNumber(pnPerso);
			}
			
			dc.updateContact(Long.parseLong(idContact), enterpriseModifie);
			
		}else{
			/*************************** CONTACT ***************************/
			
			Contact contactModifie = null;
			contactModifie = dc.loadContact(Long.parseLong(idContact));
			
			ContactGroup cgtest10 = null;
			Set<ContactGroup> set10 = contactModifie.getBooks();
			Iterator<ContactGroup> it10 = set10.iterator(); 
			while (it10.hasNext()){
		 		cgtest10 = it10.next();
		 		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n"+cgtest10.getGroupName());
			}
			
			contactModifie.setFirstname(firstname);
			contactModifie.setLastname(lastname);
			contactModifie.setEmail(email);
			contactModifie.setAdd(address);
			contactModifie.getBooks().clear();
			contactModifie.getPhones().clear();
			
			ContactGroup cgtest = null;
			Set<ContactGroup> set2 = contactModifie.getBooks();
			Iterator<ContactGroup> it2 = set2.iterator(); 
			
			while (it2.hasNext()){
		 		cgtest = it2.next();
			}
			
			try{
				cgFamily = dcg.loadContactGroup(Long.parseLong(idGroupFamily));
				System.out.println("#######################"+cgFamily.getGroupName());
			}catch(Exception e1){}
			
			try{
				cgFriends = dcg.loadContactGroup(Long.parseLong(idGroupFriends));
				System.out.println("#######################"+cgFriends.getGroupName());
			}catch(Exception e1){}
			
			try{
				cgCoworkers = dcg.loadContactGroup(Long.parseLong(idGroupCoworkers));
				System.out.println("#######################"+cgCoworkers.getGroupName());
			}catch(Exception e1){}

			
			System.out.println("ALOOOOOOOOOOOOOOO"+ groupNameFamily + groupNameFriends + groupNameCoworkers);
			
			
			if(!groupNameFamily.equals("")){ //Créer
				System.out.println("Creation famille");
				if(cgFamily==null){
					cgFamily = (ContactGroup) context.getBean("ContactGroup");
					cgFamily.setGroupName("Family");
					contactModifie.addGroupe(cgFamily);
					cgFamily.addContactAuGroupe(contactModifie);
					dcg.createContactGroup(cgFamily);
				}else{
					contactModifie.addGroupe(cgFamily);
				}
			}else if(groupNameFamily.equals("")){ //Supprime
				System.out.println("Suppression famille");
				if(cgFamily!=null){
					contactModifie.getBooks().remove(cgFamily);
					//contactModifie.removeContactFromGroup();
					cgFamily.removeContact(contactModifie);
					dcg.updateContactGroup(cgFamily);
				}
			}
			
			if(!groupNameFriends.equals("")){
				System.out.println("Creation friends");
				if(cgFriends==null){
					cgFriends = (ContactGroup) context.getBean("ContactGroup");
					cgFriends.setGroupName("Friends");
					contactModifie.addGroupe(cgFriends);
					cgFriends.addContactAuGroupe(contactModifie);
					dcg.createContactGroup(cgFamily);
				}else{
					contactModifie.addGroupe(cgFriends);
				}
			}else if(groupNameFriends.equals("")){
				System.out.println("Suppression friends");
				if(cgFriends!=null){
					contactModifie.getBooks().remove(cgFriends);
					//contactModifie.removeContactFromGroup();
					cgFriends.removeContact(contactModifie);
					dcg.updateContactGroup(cgFriends);
				}
			}
			
			if(!groupNameCoworkers.equals("")){
				System.out.println("Creation coworkers");
				if(cgCoworkers==null){
					cgCoworkers = (ContactGroup) context.getBean("ContactGroup");
					cgCoworkers.setGroupName("Coworkers");
					contactModifie.addGroupe(cgCoworkers);
					cgCoworkers.addContactAuGroupe(contactModifie);
					dcg.createContactGroup(cgCoworkers);
				}else{
					contactModifie.addGroupe(cgCoworkers);
				}
			}else if(groupNameCoworkers.equals("")){
				System.out.println("Suppression coworkers");
				if(cgCoworkers!=null){
					contactModifie.getBooks().remove(cgCoworkers);
					//contactModifie.removeContactFromGroup();
					cgCoworkers.removeContact(contactModifie);
					dcg.updateContactGroup(cgCoworkers);
				}
			}
			
//			System.out.println("#######################22"+cgFamily.getGroupName() + "|" + cgFriends.getGroupName() + "|" + cgCoworkers.getGroupName());

			if(idPhoneHome != null && !idPhoneHome.equals(""))
				dn.deletePhoneNumber(Long.parseLong(idPhoneHome));
			if(idPhoneWork != null && !idPhoneWork.equals(""))
				dn.deletePhoneNumber(Long.parseLong(idPhoneWork));
			if(idPhonePerso !=null && !idPhonePerso.equals(""))
				dn.deletePhoneNumber(Long.parseLong(idPhonePerso));		
			
	
			System.out.println("homenum: |" + homeNum);
			
			if(!homeNum.equals("")){
				pnHome.setPhoneKind("Home");
				pnHome.setPhoneNumber(homeNum);
				pnHome.setContact(contactModifie);
				contactModifie.addPhone(pnHome);
				dn.createPhoneNumber(pnHome);
			}
			if(!workNum.equals("")){
				pnWork.setPhoneKind("Work");
				pnWork.setPhoneNumber(workNum);
				pnWork.setContact(contactModifie);
				contactModifie.addPhone(pnWork);
				dn.createPhoneNumber(pnWork);
			}
			if(!persoNum.equals("")){
				pnPerso.setPhoneKind("Perso");
				pnPerso.setPhoneNumber(persoNum);
				pnPerso.setContact(contactModifie);
				contactModifie.addPhone(pnPerso);
				dn.createPhoneNumber(pnPerso);
			}	
					
			 cgtest10 = null;
			 set10 = contactModifie.getBooks();
			 it10 = set10.iterator(); 
			while (it10.hasNext()){
		 		cgtest10 = it10.next();
		 		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWwww\n"+cgtest10.getGroupName());
			}
			/*
			if(!groupNameFamily.equals("")){
				if(cgFamily==null)
					dcg.createContactGroup(cgFamily);
			}else if(groupNameFamily.equals("")){
				if(cgFamily!=null)
					contactModifie.removeContactFromGroup();
			}
			
			if(!groupNameFriends.equals("")){
				if(cgFriends==null)
					dcg.createContactGroup(cgFriends);
			}else if(groupNameFriends.equals("")){
				if(cgFriends!=null)
					contactModifie.removeContactFromGroup();
			}
			
			if(!groupNameCoworkers.equals("")){
				if(cgCoworkers==null)
					dcg.createContactGroup(cgCoworkers);
			}else if(groupNameCoworkers.equals("")){
				if(cgCoworkers!=null)
					contactModifie.removeContactFromGroup();
			}*/
			
			dc.updateContact(contactModifie);
		}

		
		pRequest.setAttribute("msg", "Contact updated.. " + lForm.getFirstname());
		
		return pMapping.findForward("success");
	}
}
