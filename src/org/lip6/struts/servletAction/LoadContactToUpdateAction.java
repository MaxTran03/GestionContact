package org.lip6.struts.servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.lip6.struts.actionForm.LoadContactToUpdateValidationForm;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import domain.Contact;
import domain.DAOContact;
import domain.Enterprise;

public class LoadContactToUpdateAction extends Action{

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
		LoadContactToUpdateValidationForm lForm = (LoadContactToUpdateValidationForm) form;
		
		
		String idContact = lForm.getIdContact();
		Contact contact = lDAOContact.searchContactByID(Long.parseLong(idContact));
		
		request.setAttribute("modification", contact);
		request.setAttribute("isEnterprise", contact instanceof Enterprise);
		
		//request.setAttribute("showAll", Boolean.TRUE);
		
		return mapping.findForward(SUCCESS);
	} 
}
