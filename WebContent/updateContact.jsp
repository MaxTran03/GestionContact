<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.Set, java.util.HashSet, domain.Contact, domain.Enterprise, domain.PhoneNumber, domain.ContactGroup, java.util.Iterator"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="updatecontact.title" /></title>
</head>
<body>
	<h2 align="center"><bean:message key="updatecontact.h2" /></h2>
	<table align="center">
		<tr>
			<td><html:link page="/CreationForward.do">
					<bean:message key="main.addcontact.link" />
				</html:link></td>
			<td><html:link page="/SupressionForward.do">
					<bean:message key="main.removecontact.link" />
				</html:link></td>
			<td><html:link page="/UpdateForward.do">
					<bean:message key="main.updatecontact.link" />
				</html:link></td>
			<td><html:link page="/SearchForward.do">
					<bean:message key="main.searchcontact.link" />
				</html:link></td>
				<td><html:link page="/AdvancedSearchForward.do"><bean:message key="main.advancesearchcontact.link" /></html:link></td>
		</tr>
	</table>

	<br>
	<form method="post" action="findContactUpdate.do">
		<table align="center">
			<tr>
				<td align="right"><i><bean:message key="searchname.field" /></i></td>
				<td align="left"><input type="text" name="firstname" size="25"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><input class="button" name="recherche" type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

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
					if (c.get(i).getClass().getName()
							.equals("domain.Enterprise"))
						enterprise = "Oui";
					else
						enterprise = "Non";
					out.println("<tr><th>" + c.get(i).getId() + "</th><th>"
							+ c.get(i).getLastname() + "</th><th>"
							+ c.get(i).getFirstname() + "</th><th>"
							+ c.get(i).getEmail() + "</th><th>" + enterprise
							+ "</th></tr>");
				}
			}
		%>
	</table>


	<br>
	<html:form method="post" action="loadContact.do">
		<%
		String s = "";
		if (request.getParameter("idContact") != null)
			s = request.getParameter("idContact");
		%>
		<table align="center">
			<tr>
				<td align="right"><i><bean:message key="chargeId.field" /></i></td> 
				<td align="left"><input type="text" name="idContact" size="25" value="<%=s%>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><input class="button" name="modification" type="submit" value="Submit" /></td>
			</tr>
		</table>
	</html:form>

<%
		Contact contact = null;
		long numSiret = 0;
		boolean isEnterprise = false;
		PhoneNumber pn1 = null;
		PhoneNumber pn2 = null;
		PhoneNumber pn3 = null;
		PhoneNumber pnHome = null;
		PhoneNumber pnPerso = null;
		PhoneNumber pnWork = null;
		ContactGroup cg1 = null;
		ContactGroup cg2 = null;
		ContactGroup cg3 = null;
		ContactGroup cgFamily = null;
		ContactGroup cgFriends = null;
		ContactGroup cgCoworkers = null;
		boolean Perso=false, Home=false, Work=false;

		if (request.getAttribute("modification") != null) {
			contact = (Contact) request.getAttribute("modification");

			if (((Boolean) request.getAttribute("isEnterprise")) == true) {
				// C'est une enterprise
				numSiret = ((Enterprise) request.getAttribute("modification")).getNumSiret();
				isEnterprise = true;
			} else {
				numSiret = 0;
				isEnterprise = false;
			}
			contact = (Contact) request.getAttribute("modification");
			
			Set<PhoneNumber> set = contact.getPhones();
			Iterator<PhoneNumber> it = set.iterator(); 
			if (it.hasNext()){
				pn1 = it.next();
				if(pn1.getPhoneKind().equals("Work"))
					pnWork=pn1;
				if(pn1.getPhoneKind().equals("Home"))
					pnHome=pn1;
				if(pn1.getPhoneKind().equals("Perso"))
					pnPerso=pn1;
				}
			if (it.hasNext()){
				pn2 = it.next();
				if(pn2.getPhoneKind().equals("Work"))
					pnWork=pn2;
				if(pn2.getPhoneKind().equals("Home"))
					pnHome=pn2;
				if(pn2.getPhoneKind().equals("Perso"))
					pnPerso=pn2;
				}
			if (it.hasNext()){
				pn3 = it.next();
				if(pn3.getPhoneKind().equals("Work"))
					pnWork=pn3;
				if(pn3.getPhoneKind().equals("Home"))
					pnHome=pn3;
				if(pn3.getPhoneKind().equals("Perso"))
					pnPerso=pn3;
				}	
				
				
			Set<ContactGroup> set3 = contact.getBooks();
			Iterator<ContactGroup> it3 = set3.iterator(); 
			if (it3.hasNext()){
				cg1 = it3.next();
				if(cg1.getGroupName().equals("Family"))
					cgFamily=cg1;
				if(cg1.getGroupName().equals("Friends"))
					cgFriends=cg1;
				if(cg1.getGroupName().equals("Coworkers"))
					cgCoworkers=cg1;
			}
				
			if (it3.hasNext()){
				cg2 = it3.next();
				if(cg2.getGroupName().equals("Family"))
					cgFamily=cg2;
				if(cg2.getGroupName().equals("Friends"))
					cgFriends=cg2;
				if(cg2.getGroupName().equals("Coworkers"))
					cgCoworkers=cg2;
			}
			
			if (it3.hasNext()){
				cg3 = it3.next();
				if(cg3.getGroupName().equals("Family"))
					cgFamily=cg3;
				if(cg3.getGroupName().equals("Friends"))
					cgFriends=cg3;
				if(cg3.getGroupName().equals("Coworkers"))
					cgCoworkers=cg3;
			}
			
			
		%>

	<br>
	<html:form method="post" action="refreshContact.do">
		
		
		<h2 align="center"><bean:message key="modify.h2" /></h2>
		<table align="center">
			<tr>
				<td align="right"><i><bean:message key="firstname.field" /></i></td>
				<td align="left"><input type="text" name="firstname" size="25" value=<%out.print(contact.getFirstname());%>></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="lastname.field" /></i></td>
				<td align="left"><input type="text" name="lastname" size="25" value=<%out.print(contact.getLastname());%>></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="email.field" /></i></td>
				<td align="left"><input type="text" name="email" size="25" value=<%out.print(contact.getEmail());%>></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="street.field" /></i></td>
				<td align="left"><input type="text" name="street" size="25" value=<%out.print(contact.getAdd().getStreet());%>></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="city.field" /></i></td>
				<td align="left"><input type="text" name="city" size="25" value=<%out.print(contact.getAdd().getCity());%>></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="zip.field" /></i></td>
				<td align="left"><input type="text" name="zip" size="25" value=<%out.print(contact.getAdd().getZip());%>></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="country.field" /></i></td>
				<td align="left"><input type="text" name="country" size="25" value=<%out.print(contact.getAdd().getCountry());%>></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="homePhone.field" /></i></td>
				<td align="left"><input type="text" name="home" size="25" value=<%if(pnHome != null)out.print(pnHome.getPhoneNumber());%>></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="persoPhone.field" /></i></td>
				<td align="left"><input type="text" name="perso" size="25" value=<%if(pnPerso != null)out.print(pnPerso.getPhoneNumber());%>></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="workPhone.field" /></i></td>
				<td align="left"><input type="text" name="work" size="25" value=<%if(pnWork != null)out.print(pnWork.getPhoneNumber());%>></td>
			</tr>

			<%
				boolean isFamily = false;
				boolean isFriends = false;
				boolean isCoworkers = false;
				for(ContactGroup cg : contact.getBooks()){
					if(cg.getGroupName().equals("Family"))
						isFamily = true;
					if(cg.getGroupName().equals("Friends"))
						isFriends = true;
					if(cg.getGroupName().equals("Coworkers"))
						isCoworkers = true;
				}
			%>

			<tr>
				<td align="right"><i><bean:message key="groupFamily.box" /></i></td>
				<td align="left"><input type="checkbox" id="id_box1" name="family" value="familles" <%if(isFamily)out.print("checked");%>>
					<input type="hidden" id="id_box" name="cboxFamily" value="false"></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="groupFriends.box" /></i></td>
				<td align="left"><input type="checkbox" id="id_box2" name="friends" value="amis" <%if(isFriends)out.print("checked");%>> 
					<input type="hidden" id="id_box" name="cboxFriends" value="false"></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="groupCoworkers.box" /></i></td>
				<td align="left"><input type="checkbox" id="id_box3" name="coworkers" value="collegue" <%if(isCoworkers)out.print("checked");%>> 
					<input type="hidden" id="id_box" name="cboxCoworkers" value="false"></td>
			</tr>

			<%
				if (isEnterprise) {
			%>

			<tr>
				<td align="right"><i><bean:message key="enterprise.box" /></i></td>
				<td align="left"><input type="checkbox" id="id_box" name="cbox" value="entreprise" checked="<%out.print(isEnterprise);%>"> 
					<input type="hidden" id="id_box" name="cbox" value="false"></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="numSiret.field" /></i></td>
				<td align="left"><input type="text" name="numSiret" size="25" value=<%out.print(numSiret);%>></td>
			</tr>

			<%
				} else {
			%>
			<tr>
				<td align="right"><input type="checkbox" id="id_box" name="cbox" value="entreprise" style="visibility: hidden"> 
					<input type="hidden" id="id_box" name="cbox" value="false"></td>
			</tr>

			<tr>
				<td align="right"><input type="text" name="numSiret" size="25" style="visibility: hidden"></td>
			</tr>

			<%
				}
			%>
			
			<tr>
				<td align="right"><i >ID Contact:</i></td>
				<td align="left"><input type="text" name="ideo" size="0" value="<%out.print(contact.getId());%>" ></td>
			</tr>
			
			<%if(pnHome != null){ %>
			<tr>
				<td align="right"><i >ID Phone Home:</i></td>
				<td align="left"><input type="text" name="idphonehome" size="0"  value="<%out.print(pnHome.getPhoneNumberId());%>" ></td>
			</tr>
			<% }%>
			
			<%if(pnWork != null){ %>
			<tr>
				<td align="right"><i >ID Phone Work:</i></td>
				<td align="left"><input type="text" name="idphonework" size="0"  value="<%out.print(pnWork.getPhoneNumberId());%>" ></td>
			</tr>
			<% }%>
					
			<%if(pnPerso != null){ %>
			<tr>
				<td align="right"><i >ID Phone Perso:</i></td>
				<td align="left"><input type="text" name="idphoneperso" size="0"  value="<%out.print(pnPerso.getPhoneNumberId());%>" ></td>
			</tr>
			<% }%>
			
			<%if(cgFamily != null){ %>
			<tr>
				<td align="right"><i >ID Group Family:</i></td>
				<td align="left"><input type="text" name="idgroupfamily" size="0"  value="<%out.print(cgFamily.getGroupId());%>" ></td>
			</tr>
			<% }%>
			
			<%if(cgFriends != null){ %>
			<tr>
				<td align="right"><i >ID Group Friends:</i></td>
				<td align="left"><input type="text" name="idgroupfriends" size="0"  value="<%out.print(cgFriends.getGroupId());%>" ></td>
			</tr>
			<% }%>
			
			<%if(cgCoworkers != null){ %>
			<tr>
				<td align="right"><i style="visibility: hidden">ID Group Coworkers:</i></td>
				<td align="left"><input type="text" name="idgroupcoworkers" size="0"  value="<%out.print(cgCoworkers.getGroupId());%>" ></td>
			</tr>
			<% }%>
			
			<tr>
				<td align="right"><input class="button" name="validation" type="submit" value="Submit" /> </td>
				<td align="left"><input class="button" type="reset" value="Reset"></td>
			</tr>
		</table>
		
	</html:form>
	<%}%>
	<br>
	<div class="msg" align="center" style="color: green;">${msg}</div>

</body>
</html:html>