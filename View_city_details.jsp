<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<html>
<head>
<title>City Details</title>
</head>
<body>
<h2>City Details</h2>
<%

try {

	String connectionURL = "jdbc:postgresql://localhost:5432/dbms_project";
	Connection con = null;
	String url = "jdbc:postgresql://localhost:5432/dbms_project";
	String username = "dbms_project";
	String password = "1234";

	Class.forName("org.postgresql.Driver");
	con = DriverManager.getConnection(url, username, password);
	System.out.println("Printing connection object "+con);


	PreparedStatement st = con.prepareStatement("select * from city_details");
		
	ResultSet rs = st.executeQuery();
	

%>

<TABLE cellpadding="30" border="1" style="background-color: #fecabf;">


<TR>

<TD>City Id</TD>
<TD>City name</TD>
<TD>State name</TD>

</TR>





<%

while (rs.next()) {

%>

<TR>

<TD><%=rs.getInt(1)%></TD>
<TD><%=rs.getString(2)%></TD>
<TD><%=rs.getString(3)%></TD>

</TR>

<% } %>

<%

rs.close();
st.close();
con.close();
} catch (Exception ex) {
	
%>

</font>
<font size="+3" color="red"></b>

<%
out.println("Unable to connect to database.");
}

%>
</TABLE>
</font>
</body>
</html>