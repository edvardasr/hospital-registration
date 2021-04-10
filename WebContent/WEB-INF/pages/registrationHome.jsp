<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <h1>Pacientu registavimo sistema</h1>
	
	<table>
      <tr>
        <form method="POST" action="/RestServiceHospital/addReservation" modelAttribute="reservation">
          <td><label>Pridekite pacienta</label></td>
          <td><input type="submit" name="add" value=Prideti /></td> 
        </form>
      </tr>
	 
      <tr>
        <form method="GET" action="/RestServiceHospital/reservationList">
          <td> <label>Iveskite ID<label></td>
      </tr>
	 
      <tr>
          <td> <input type="text" id="id" name="id" sizwidth:100%"></td>
   	      <td> <input type="submit" name="submit" value=Perziureti /> </td>
   	  </tr>
   	    </form>
   	    
	</table>
</body>
</html>
