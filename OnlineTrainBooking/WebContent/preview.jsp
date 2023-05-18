<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import=" java.io.IOException,java.sql.CallableStatement, java.sql.Connection,java.sql.DriverManager,java.sql.Types,javax.servlet.RequestDispatcher,javax.servlet.ServletException,javax.servlet.annotation.WebServlet,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Travel details</h2>
<table border = "1">

<% 
Double fare = 0.0;
Double tcost=0.0;
ArrayList<Double> individualFare =new ArrayList<>();
try {
	String a = request.getParameter("to");
	String b = request.getParameter("from");
	String tno = request.getParameter("train_no");
	String classType = request.getParameter("train_class");

	Class.forName("org.postgresql.Driver");
	Connection c = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
	CallableStatement cstmt = c.prepareCall("CALL trainFare(?, ?, ?, ?, ?)");
	cstmt.setString(1, a);
	cstmt.setString(2, b);
	cstmt.setInt(3, Integer.parseInt(tno));
	cstmt.setString(4, classType);
	cstmt.setInt(5, 0);
	cstmt.registerOutParameter(1, Types.NUMERIC); // Register output parameter
	cstmt.execute();
	 Object fp = cstmt.getObject(1);
     fare = (fp != null) ? ((Number) fp).doubleValue() : null;

	cstmt.close();
	c.close();
} catch (Exception e) {
	e.printStackTrace();
}
//System.out.print(ticketCost);
System.out.print(fare);
for(int i=1;i<6;i++){
	 //String pname = request.getParameter("pname" + i);
	 Double ticketCost=0.0;
     int age = Integer.parseInt(request.getParameter("age" + i));
     //String gender = request.getParameter("gender" + i);
     
     if (age > 65 )//&& gender.equals("male")) || (age > 58 && gender.equals("female")))
			{ticketCost = fare * 0.75;System.out.print(ticketCost);}
	 else if (age < 12)
	 {			ticketCost = fare * 0.50;System.out.print(ticketCost);}
	 else
			ticketCost = fare;
     
     individualFare.add(ticketCost);
     tcost+=ticketCost;
}


%>

<tr><td><b>source</b></td><td><%=request.getParameter("from") %></td></tr>
<tr><td>destination</td><td><%=request.getParameter("to") %></td></tr>
<tr><td>train name</td><td><%=request.getParameter("train") %></td></tr>
<tr><td>train number</td><td><%=request.getParameter("train_no") %></td></tr>
<tr><td>class</td><td><%=request.getParameter("train_class") %></td></tr>
<tr><td>date</td><td><%=request.getParameter("date") %></td></tr>
<tr><td>fare</td><td><%=tcost %></td></tr>

</table>
<h2>Passengers</h2>

<table border = "1">
<tr><td>NAME</td><td>AGE</td><td>GENDER</td><td>fare</td></tr>
<tr><td><%=request.getParameter("name1") %></td><td><%=request.getParameter("age1") %></td><td><%=request.getParameter("gender1") %></td><td><%=individualFare.get(0) %></td></tr>
<tr><td><%=request.getParameter("name2") %></td><td><%=request.getParameter("age2") %></td><td><%=request.getParameter("gender2") %></td><td><%=individualFare.get(1) %></td></tr>
<tr><td><%=request.getParameter("name3") %></td><td><%=request.getParameter("age3") %></td><td><%=request.getParameter("gender3") %></td><td><%=individualFare.get(2) %></td></tr>
<tr><td><%=request.getParameter("name4") %></td><td><%=request.getParameter("age4") %></td><td><%=request.getParameter("gender4") %></td><td><%=individualFare.get(3) %></td></tr>
<tr><td><%=request.getParameter("name5") %></td><td><%=request.getParameter("age5") %></td><td><%=request.getParameter("gender5") %></td><td><%=individualFare.get(4) %></td></tr>
</table>
<br>
<button onclick="window.history.back()">edit</button>
<script>
function finalScreen(){
	console.log("hello");
	document.getElementById("name1").value="<%=request.getParameter("name1") %>";
	document.getElementById("age1").value="<%=request.getParameter("age1") %>";
	document.getElementById("gender1").value="<%=request.getParameter("gender1") %>";
	
	document.getElementById("name2").value="<%=request.getParameter("name2") %>";
	document.getElementById("age2").value="<%=request.getParameter("age2") %>";
	document.getElementById("gender2").value="<%=request.getParameter("gender2") %>";
	
	document.getElementById("name3").value="<%=request.getParameter("name3") %>";
	document.getElementById("age3").value="<%=request.getParameter("age3") %>";
	document.getElementById("gender3").value="<%=request.getParameter("gender3") %>";

	document.getElementById("name4").value="<%=request.getParameter("name4") %>";
	document.getElementById("age4").value="<%=request.getParameter("age4") %>";
	document.getElementById("gender4").value="<%=request.getParameter("gender4") %>";
	
	document.getElementById("name5").value="<%=request.getParameter("name5") %>";
	document.getElementById("age5").value="<%=request.getParameter("age5") %>";
	document.getElementById("gender5").value="<%=request.getParameter("gender5") %>";
	
	document.getElementById("submit_fare").value="<%=request.getParameter("fare") %>";
	document.getElementById("submit_to").value="<%=request.getParameter("to") %>";
	document.getElementById("submit_from").value="<%=request.getParameter("from") %>";
	document.getElementById("submit_train_no").value="<%=request.getParameter("train_no") %>";
	document.getElementById("submit_train_class").value="<%=request.getParameter("train_class") %>";
	document.getElementById("submit_date").value="<%=request.getParameter("date") %>";
	document.getElementById("xyz").submit();
}
</script>

<form method="POST" action="finalServlet" id="xyz">
<input type="hidden" name="name1" id="name1">
<input type="hidden" name="age1" id="age1">
<input type="hidden" name="gender1" id="gender1">

<input type="hidden" name="name2" id="name2">
<input type="hidden" name="age2" id="age2">
<input type="hidden" name="gender2" id="gender2">

<input type="hidden" name="name3" id="name3">
<input type="hidden" name="age3" id="age3">
<input type="hidden" name="gender3" id="gender3">

<input type="hidden" name="name4" id="name4">
<input type="hidden" name="age4" id="age4">
<input type="hidden" name="gender4" id="gender4">

<input type="hidden" name="name5" id="name5">
<input type="hidden" name="age5" id="age5">
<input type="hidden" name="gender5" id="gender5">

 <input type="hidden" name="fare" id="submit_fare">
 <input type="hidden" name="to" id="submit_to">
 <input type="hidden" name="from" id="submit_from">
 <input type="hidden" name="train" id="submit_train">
 <input type="hidden" name="train_no" id="submit_train_no">
 <input type="hidden" name="train_class" id="submit_train_class">
 <input type="hidden" name="date" id="submit_date">



<input type="button" value="BOOK" onclick="finalScreen()"></form>
</body>

</html>