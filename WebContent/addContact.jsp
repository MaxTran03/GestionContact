<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="addcontact.title" /></title>
<html:base />
</head>
<body bgcolor="white">
	<h2 align="center"><bean:message key="addcontact.h2" /></h2>
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
	<html:form action="newContact.do">
		<table align="center">
			<tr>
				<td align="right"><i><bean:message key="firstname.field" /></i></td>
				<td align="left"><html:text property="firstname" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="firstname" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="lastname.field" /></i></td>
				<td align="left"><html:text property="lastname" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="lastname" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="email.field" /></i></td>
				<td align="left"><html:text property="email" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="email" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="street.field" /></i></td>
				<td align="left"><html:text property="street" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="street" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="city.field" /></i></td>
				<td align="left"><html:text property="city" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="city" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="zip.field" /></i></td>
				<td align="left"><html:text property="zip" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="zip" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="country.field" /></i></td>
				<td align="left"><html:text property="country" size="25" maxlength="25" /></td>
				<td style="color: red;"><html:errors property="country" /></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="homePhone.field" /></i></td>
				<td align="left"><html:text property="home" size="25" maxlength="25" /></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="persoPhone.field" /></i></td>
				<td align="left"><html:text property="perso" size="25" maxlength="25" /></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="workPhone.field" /></i></td>
				<td align="left"><html:text property="work" size="25" maxlength="25" /></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="groupFamily.box" /></i></td>
				<td align="left"><html:checkbox property="family" /> 
					<html:hidden property="cboxFamily" value="false" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="groupFriends.box" /></i></td>
				<td align="left"><html:checkbox property="friends" /> 
					<html:hidden property="cboxFriends" value="false" /></td>
			</tr>
			<tr>
				<td align="right"><i><bean:message key="groupCoworkers.box" /></i></td>
				<td align="left"><html:checkbox property="coworkers" /> 
					<html:hidden property="cboxCoworkers" value="false" /></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="enterprise.box" /></i></td>
				<td align="left"><html:checkbox property="enterprise" onchange="AddRandomChampsEntreprise()"/> 
					<html:hidden property="cboxEnterprise" value="false" /></td>
			</tr>

			<tr>
				<td align="right"><i><bean:message key="numSiret.field" /></i></td>
				<td align="left"><html:text property="numSiret" size="25" maxlength="25" /></td>
			</tr>

			<script>
				function Verif() {
					if (document.getElementById(1).value = '') {
						alert("non mais allo");
						return false;
					} else
						return true;
				}

				function AddRandomChampsEntreprise() {

					if (document.getElementById("n2").type == "text")
						document.getElementById("n2").type = "hidden";
					else {
						document.getElementById("n2").type = "text";

					}

					if (document.getElementById("id_box").checked == false)
						document.getElementById("n2").value = "";
				}

				function RandomChamps() {

					for (i = 1; i <= 7; i++) {
						document.getElementById(i).value = "";
					}

					document.getElementById("n1").value = "";
					if (document.getElementById("id_box").checked == true)
						document.getElementById("n2").value = "";

					var ListeCar = new Array("a", "b", "c", "d", "e", "f", "g",
							"h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
							"r", "s", "t", "u", "v", "w", "x", "y", "z");
					var ListeNb = new Array("0", "1", "2", "3", "4", "5", "6",
							"7", "8", "9");

					var FirstNameTab = new Array("Bob", "Alice", "Pierre",
							"Paul", "Jacques", "Xavier", "Sandrine", "Cindy",
							"Kévin", "Joseph", "Nabila", "Yohan", "Franck",
							"Farid", "Liza");

					var LastNameTab = new Array("Dupont", "Durant", "Riemann",
							"Euler", "Gauss", "Gavaros", "Tesla", "Maxwell",
							"Poincarré", "Debré", "Hilbert", "Morrowind",
							"Monnet");

					var EmailTab = new Array("orange", "sfr", "free", "yahoo",
							"laposte", "bouygues", "virgine", "hotmail",
							"gmail", "outlook", "voila", "aol");

					var DomaineTab = new Array("fr", "com", "net");

					var StreetTab = new Array("De la paix", "Alma", "Ampère",
							"Jean Jaurès", "Victor Hugo",
							"François Mitterrand", "Montée de la Butte",
							"Quai de Bondy", "Place Benoît-Crépu",
							"Antoine-Charial", "Sainte-Catherine",
							"Place de la Croix-Rousse", "Félix-Faure",
							"Lafayette");

					var CityTab = new Array("Paris", "Marseille", "Lyon",
							"Toulouse", "Nice", "Nantes", "Strasbourg",
							"Montpellier", "Bordeaux", "Lille", "Rennes",
							"Reims", "Le Havre", "Londres", "Berlin", "Madrid",
							"Rome", "Bucarest", "Vienne", "Hambourg",
							"Budapest", "Varsovie");

					document.getElementById("pk").selectedIndex = Math
							.floor(Math.random() * 3);

					document.getElementById("grp").selectedIndex = Math
							.floor(Math.random() * 3);

					document.getElementById(1).value = FirstNameTab[Math
							.floor(Math.random() * FirstNameTab.length)];

					document.getElementById(2).value = LastNameTab[Math
							.floor(Math.random() * LastNameTab.length)];

					if (Math.random() < 0.5)
						for (j = 0; j < 5 + Math.floor(Math.random() * 4); j++)
							document.getElementById(3).value += ListeCar[Math
									.floor(Math.random() * 26)];
					else
						document.getElementById(3).value = document
								.getElementById(2).value
								+ "." + document.getElementById(1).value;

					if (Math.random() < 0.5)
						for (j = 0; j < 1 + Math.floor(Math.random() * 2); j++)
							document.getElementById(3).value += ListeNb[Math
									.floor(Math.random() * 10)];

					document.getElementById(3).value += "@";
					document.getElementById(3).value += EmailTab[Math
							.floor(Math.random() * EmailTab.length)];
					document.getElementById(3).value += ".";
					document.getElementById(3).value += DomaineTab[Math
							.floor(Math.random() * DomaineTab.length)];

					document.getElementById(4).value = StreetTab[Math
							.floor(Math.random() * StreetTab.length)];

					document.getElementById(5).value = CityTab[Math.floor(Math
							.random()
							* CityTab.length)];

					for (j = 0; j < 5; j++)
						document.getElementById(6).value += ListeNb[Math
								.floor(Math.random() * 10)];

					if (document.getElementById(5).value == "Londres")
						document.getElementById(7).value = "UK";
					else if (document.getElementById(5).value == "Berlin")
						document.getElementById(7).value = "Germany";
					else if (document.getElementById(5).value == "Rome")
						document.getElementById(7).value = "Italy";
					else if (document.getElementById(5).value == "Madrid")
						document.getElementById(7).value = "Spain";
					else if (document.getElementById(5).value == "Bucarest")
						document.getElementById(7).value = "Romania";
					else if (document.getElementById(5).value == "Vienne")
						document.getElementById(7).value = "Austria";
					else if (document.getElementById(5).value == "Hambourg")
						document.getElementById(7).value = "Germany";
					else if (document.getElementById(5).value == "Budapest")
						document.getElementById(7).value = "Hungary";
					else if (document.getElementById(5).value == "Varsovie")
						document.getElementById(7).value = "Poland";
					else
						document.getElementById(7).value = "France";

					if (Math.random() < 0.33)
						document.getElementById("n1").value = "01";
					else if (Math.random() < 0.66)
						document.getElementById("n1").value = "06";
					else
						document.getElementById("n1").value = "07";

					for (j = 0; j < 8; j++)
						document.getElementById("n1").value += ListeNb[Math
								.floor(Math.random() * 10)];

					for (j = 0; j < 3 + Math.floor(Math.random() * 4); j++)
						if (document.getElementById("id_box").checked == true)
							document.getElementById("n2").value += ListeNb[Math
									.floor(Math.random() * 10)];
				}
			</script>

			<tr>
				<td align="right"><html:submit value="Submit" /></td>
				<td><html:reset value="Reset" />
					<input class="button" type="button" value="Random" onClick="RandomChamps()" /></td>
			</tr>

		</table>
	</html:form>
</body>
</html:html>