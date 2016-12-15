<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="java.util.List, domain.Contact, domain.Enterprise "%>
	<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recherche d'un contact par modele</title>
</head>
<body>
	<h2 align="center">Search by model</h2>
	<table align="center">
		<tr>
			<td><html:link page="/CreationForward.do"><bean:message key="main.addcontact.link" /></html:link></td>
			<td><html:link page="/SupressionForward.do"><bean:message key="main.removecontact.link" /></html:link></td>
			<td><html:link page="/UpdateForward.do"><bean:message key="main.updatecontact.link" /></html:link></td>
			<td><html:link page="/SearchForward.do"><bean:message key="main.searchcontact.link" /></html:link></td>
			<td><html:link page="/AdvancedSearchForward.do"><bean:message key="main.advancesearchcontact.link" /></html:link></td>
		</tr>
	</table>

	<form method="post" action="searchModels">
		<table>
			
			<tr>
				<td><i>First name: <input type="text" name="firstname"
						size="25"></i></td>
			</tr>
			<tr>
				<td><i>Last name: <input type="text" name="lastname"
						size="25"></i></td>
			</tr>
			<tr>
				<td><i>Email: <input type="text" name="email" size="25"></i></td>
			</tr>
			
			<tr>
				<td><input class="button" type="submit" name="param" value="Submit" /></td>
			</tr>
		</table>
	</form>
	<table border = 5>
	<tr>
		<th>ID</th>
       	<th>Last Name</th>
       	<th>First Name</th>
       	<th>Email</th>
       	<th>Enterprise</th>
   	</tr>
   	
	<!-- Parcours la liste des contacts -->
		<%
			
			List<Contact> c = null;
			String enterprise;
			System.out.println("recu requete");
			
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
</html>