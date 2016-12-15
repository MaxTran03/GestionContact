<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajout de contact</title>
</head>
<body>
	<form method="post" action="newContact">
	
		<table>
			<tr>
				<th><h2>Ajouter un contact:</h2></th>
			<tr>
			<tr>
				<td><i>Please enter your first name: <input id=1 type="text"
						name="firstname" size="25" 
						
						></i></td>
			</tr>
			<tr>
				<td><i>Please enter your last name: <input id=2 type="text"
						name="lastname" size="25"></i></td>
			</tr>
			<tr>
				<td><i>Please enter your email: <input id=3 type="text"
						name="email" size="25"></i></td>
			</tr>
			<tr>
				<td><i>Please enter your street: <input id=4 type="text"
						name="street" size="25"></i></td>
			</tr>
			<tr>
				<td><i>Please enter your city: <input id=5 type="text"
						name="city" size="25"></i></td>
			</tr>
			<tr>
				<td><i>Please enter your zip: <input id=6 type="text" name="zip"
						size="25"></i></td>
			</tr>
			<tr>
				<td><i>Please enter your country: <input id=7 type="text"
						name="country" size="25"></i></td>
			</tr>

			<tr>
				<td><i>Please enter your phone kind: </i><select
					name="phoneKind">
						<option value="home" selected>Home</option>
						<option value="work">Work</option>
						<option value="perso">Personal</option>
				</select></td>
			</tr>

			<tr>
				<td><i>Please enter your phone number: <input id="n1" type="text"
						name="phoneNumber" size="25"></i></td>
			</tr>

			<tr>
				<td><i>Please enter your group: </i><select name="groupName">
						<option value="friends" selected>Friends</option>
						<option value="family">Family</option>
						<option value="coworkers">Coworkers</option>
				</select></td>
			</tr>

			<tr>
				<td><i>Entreprise: <input type="checkbox" id="id_box"
						name="cbox" value="entreprise"><input type="hidden"
						id="id_box" name="cbox" value="false"></i></td>
			</tr>

			<tr>
				<td><i>Please enter your enterprise number: <input id="n2"
						type="text" name="numSiret" size="25"></i></td>
			</tr>
			
			<script>
			function test(){
				//document.getElementById('fname').value = 'newtext';
				var ListeCar = new Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
				var ListeNb = new Array("0","1","2","3","4","5","6","7","8","9");
				document.getElementById("id_box").checked=true;
				
				
				for(i = 1; i <= 7; i++){
					document.getElementById(i).value ="";
				}
				
				document.getElementById("n1").value ="";
				document.getElementById("n2").value ="";
					
				
				for(i = 1; i <= 7; i++){
					for(j = 0; j < 3 + Math.floor(Math.random()*4); j++){
						document.getElementById(i).value += ListeCar[Math.floor(Math.random()*26)];
						
					}
				}
				for(j = 0; j < 3 + Math.floor(Math.random()*4); j++){
					document.getElementById("n1").value += ListeNb[Math.floor(Math.random()*10)];
					document.getElementById("n2").value += ListeNb[Math.floor(Math.random()*10)];
				}
			}
			</script>
			


			<tr>
				<td><input class="button" type="submit" value="Submit" /> 
					<input class="button" type="reset" value="Reset" /> 
					<input class="button" type="button" value="Random"  onClick="test()"/></td>
			</tr>

		</table>
	</form>
</body>
</html>