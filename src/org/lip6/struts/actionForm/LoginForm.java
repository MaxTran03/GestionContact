package org.lip6.struts.actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if ((name == null) || (name.length() == 0))
			errors.add("name", new ActionMessage("error.name.required"));

		if ((password == null) || (password.length() == 0))
			errors.add("password", new ActionMessage("error.password.required"));
		
		if ((!name.equals(password)))
			errors.add("notmatching", new ActionMessage("error.matching.required"));

		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.name = null;
		this.password = null;
	}
}
