<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.Statement,java.sql.ResultSet" %>
<% 
String s1="";//<select>";
String s2="";
Connection c=null;
Statement s =null;
ResultSet rs=null;
try {
	Class.forName("org.postgresql.Driver");
	c = DriverManager.getConnection(
			"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
	 s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	rs = s.executeQuery("select * from trains_data");
	rs.absolute(0);
	while (rs.next()) {
		//System.out.print(rs.getString(2));
		s2+="<option>"+rs.getString(5)+"</option>";
		s1+="<option>"+rs.getString(4)+"</option>";
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
//s1+"</select>";
out.print(s1+"end"+s2);
%>  