package org.lip6.struts.actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoadContactToUpdateValidationForm extends ActionForm{

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

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
		ActionErrors errors = new ActionErrors();
		
		if(getIdContact() == null || getIdContact().length() < 0){
			errors.add("id",new ActionMessage("error.load.id.required"));
		}
		
		return errors;
	}
}
