package org.lip6.struts.servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.lip6.struts.actionForm.DeleteContactValidationForm;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import domain.DAOContact;

public class DeleteContactAction extends Action{
	private static final String SUCCESS = "success";
	
	ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
	DAOContact lDAOContact = (DAOContact) context.getBean("DAOContact");
	
	/**
	 * Delete a contact with his ID
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DeleteContactValidationForm lForm = (DeleteContactValidationForm) form;
		
		String idContact = lForm.getIdContact();
		
		if(idContact == null || idContact.length() < 0 || idContact.equals(""))
			return mapping.findForward("error");
		else{
			lDAOContact.deleteContact(Long.parseLong(idContact));
			request.setAttribute("msg","Contact deleted with id " + Long.parseLong(lForm.getIdContact()));
			return mapping.findForward(SUCCESS);
		}
	}
}
