<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
  } );
</script>
</head>
<body>
	<form:form method="POST" action="${pageContext.request.contextPath}/addReservation" modelAttribute="reservation">
		<table>
			<tr>
				<td><form:label path="name">Vardas</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="surname">Pavarde</form:label></td>
				<td><form:input path="surname" /></td>
			</tr>
			
			<tr>
				<td><form:label path="date">Data</form:label></td>
				<td><form:input type="text" id="datepicker" path="date" /></td>
			</tr>
			<tr>
				<td><form:label path="id">Id</form:label></td>
				<td><form:input path="id" /></td>
			</tr>
	
			<tr>
				<td><input type="submit" name="submit" value="Patvirtinti" /></td>
				<td><input type="submit" name="cancel" value="Atmesti" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>