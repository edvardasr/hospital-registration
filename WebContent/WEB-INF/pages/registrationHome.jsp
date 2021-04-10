<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <h1>Pacientu registavimo sistema</h1>
	
	<table>
      <tr>
        <form method="POST" action="/RestServiceHospital/addReservation" modelAttribute="reservation">
          <label>Pridekite pacienta<label>
          <input type="submit" name="add" value=Prideti /> 
        </form>
      </tr>
	 
	  <tr> <br> <br> </tr>
	 
      <tr>
        <form method="GET" action="/RestServiceHospital/reservationList">
          <td> <label>Noredami peziureti iveskite ID<label></td>
      </tr>
	 
      <tr>
          <td> <input type="text" id="id" name="id">
   	      <input type="submit" name="submit" value=Perziureti /> </td>
   	  </tr>
   	    </form>
   	    
	</table>
</body>
</html>