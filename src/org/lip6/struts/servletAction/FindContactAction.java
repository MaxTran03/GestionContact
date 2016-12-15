package org.lip6.struts.servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.lip6.struts.actionForm.FindContactValidationForm;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import domain.Contact;
import domain.DAOContact;

public class FindContactAction extends Action{
	private static final String SUCCESS = "success";
	
	ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
	DAOContact lDAOContact = (DAOContact) context.getBean("DAOContact");
	
	/**
	 * Retrieve all contact by his name
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		FindContactValidationForm lForm = (FindContactValidationForm) form;
		
		String firstname = lForm.getFirstname();
		
		List<Contact> contacts = lDAOContact.searchContact(firstname);
		request.setAttribute("recherche", contacts);
		//request.setAttribute("showAll", Boolean.TRUE);
		
		return mapping.findForward(SUCCESS);
	} 
}
