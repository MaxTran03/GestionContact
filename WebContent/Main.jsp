<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="main.title"/></title>
<html:base/>
</head>
<body>

<h1 align="center"><bean:message key="label.welcome"/> </h1>
	<table align="center">
		<tr>
			<td><html:link page="/CreationForward.do"><bean:message key="main.addcontact.link" /></html:link></td>
			<td><html:link page="/SupressionForward.do"><bean:message key="main.removecontact.link" /></html:link></td>
			<td><html:link page="/UpdateForward.do"><bean:message key="main.updatecontact.link" /></html:link></td>
			<td><html:link page="/SearchForward.do"><bean:message key="main.searchcontact.link" /></html:link></td>
			<td><html:link page="/AdvancedSearchForward.do"><bean:message key="main.advancesearchcontact.link" /></html:link></td>
		</tr>
	</table>
</body>
</html:html>