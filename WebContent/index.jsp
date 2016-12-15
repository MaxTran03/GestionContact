<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="login.title" /></title>
<html:base />
</head>
<body>
	<h2 align="center"><bean:message key="login.h2" /></h2>
	
	<html:form action="login.do" method="post" focus="name">
		<table align="center">
			<tr>
				<td align="right"><i><bean:message key="username.field" /></i></td>
				<td align="left"><html:text property="name" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="name" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="password.field" /></i></td>
				<td align="left"><html:text property="password" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="password" /></td>
			</tr>
			<tr>
				<td align="right"><html:submit value="Submit" /></td>
				<td align="left"><html:reset value="Reset" /></td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>