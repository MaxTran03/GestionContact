package org.lip6.struts.servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.lip6.struts.actionForm.LoginForm;

public class LoginAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String result = null;
		String name = ((LoginForm) form).getName();
		String password = ((LoginForm) form).getPassword();

		if (name.equals(password)) {
			result = "Success";
		} else {
			result = "Fail";
		}

		return mapping.findForward(result);
	}

}
