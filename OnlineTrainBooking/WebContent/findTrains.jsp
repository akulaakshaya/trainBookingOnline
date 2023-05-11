<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.Statement,java.sql.ResultSet" %>
<% 

String s1="";
Connection c=null;
Statement s =null;
ResultSet rs=null;
String a=request.getParameter("to");
String b=request.getParameter("from");
try {
	Class.forName("org.postgresql.Driver");
	c = DriverManager.getConnection(
			"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
	 s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	rs = s.executeQuery("select * from trains_data where trn_start='"+a+"' and trn_end='"+b+"';");
	rs.absolute(0);
	while (rs.next()) {
	
		s1+="<option>"+rs.getString(2)+"</option>";
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
out.print(s1);
%>  