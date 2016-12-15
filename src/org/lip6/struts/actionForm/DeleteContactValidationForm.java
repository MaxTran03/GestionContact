package org.lip6.struts.actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class DeleteContactValidationForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idContact;

	public String getIdContact() {
		return idContact;
	}

	public void setIdContact(String idContact) {
		this.idContact = idContact;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.idContact = null;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
		ActionErrors errors = new ActionErrors();
		
		if(getIdContact() == null || getIdContact().length() < 0){
			errors.add("idDelete",new ActionMessage("error.delete.id.required"));
		}
		
		return errors;
	}
}
