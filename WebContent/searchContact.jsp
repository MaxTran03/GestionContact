<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, domain.Contact, domain.Enterprise "%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="searchcontact.title" /></title>
<html:base />
</head>
<body>

	<h2 align="center"><bean:message key="searchcontact.h2" /></h2>
	<table align="center">
		<tr>
			<td><html:link page="/CreationForward.do"><bean:message key="main.addcontact.link" /></html:link></td>
			<td><html:link page="/SupressionForward.do"><bean:message key="main.removecontact.link" /></html:link></td>
			<td><html:link page="/UpdateForward.do"><bean:message key="main.updatecontact.link" /></html:link></td>
			<td><html:link page="/SearchForward.do"><bean:message key="main.searchcontact.link" /></html:link></td>
			<td><html:link page="/AdvancedSearchForward.do"><bean:message key="main.advancesearchcontact.link" /></html:link></td>
		</tr>
	</table>
	
	<br>
	<html:form action="findContact.do">
		<table align="center">
			<tr>
				<td align="right"><i><bean:message key="searchname.field" /></i></td>
				<td align="left"><html:text property="firstname" size="25" maxlength="25" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><input class="button" type="submit" name="recherche" value="Submit" /></td>
			</tr>
		</table>
	</html:form>

	<br>
	<table border=3 align="center">
		<tr>
			<th><bean:message key="id.column" /></th>
			<th><bean:message key="lastname.column" /></th>
			<th><bean:message key="firstname.column" /></th>
			<th><bean:message key="email.column" /></th>
			<th><bean:message key="enterprise.column" /></th>
		</tr>

		<!-- Parcours la liste des contacts -->
		<%
			List<Contact> c = null;
			String enterprise;
			if (request.getAttribute("recherche") != null) {
				c = (List<Contact>) request.getAttribute("recherche");
				for (int i = 0; i < c.size(); i++) {
					if (c.get(i).getClass().getName().equals("domain.Enterprise"))
						enterprise = "Oui";
					else
						enterprise = "Non";
					out.println("<tr><th>" + c.get(i).getId() + "</th><th>" 
							+ c.get(i).getLastname() + "</th><th>"
							+ c.get(i).getFirstname() + "</th><th>"
							+ c.get(i).getEmail() + "</th><th>"
							+ enterprise + "</th></tr>");
				}
			}
		%>
	</table>


</body>
</html:html>