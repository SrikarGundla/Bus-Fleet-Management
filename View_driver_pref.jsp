<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<html>
<head>
<title>Driver working days</title>
</head>
<body>
<h2>Driver working days</h2>
<%

try {

	Connection con = null;
	String url = "jdbc:postgresql://localhost:5432/dbms_project";
	String username = "dbms_project";
	String password = "1234";

	Class.forName("org.postgresql.Driver");
	con = DriverManager.getConnection(url, username, password);
	System.out.println("Printing connection object "+con);


	PreparedStatement st = con.prepareStatement("select * from driver_pref");
		
	ResultSet rs = st.executeQuery();
	

%>

<TABLE cellpadding="15" border="1" style="background-color: #fecabf;">


<TR>

<TD>Driver Id</TD>
<TD>Sunday</TD>
<TD>Monday</TD>
<TD>Tuesday</TD>
<TD>Wednesday</TD>
<TD>Thursday</TD>
<TD>Friday</TD>
<TD>Saturday</TD>

</TR>





<%

while (rs.next()) {

%>

<TR>

<TD><%=rs.getInt(1)%></TD>
<TD><%=rs.getInt(2)%></TD>
<TD><%=rs.getInt(3)%></TD>
<TD><%=rs.getInt(4)%></TD>
<TD><%=rs.getInt(5)%></TD>
<TD><%=rs.getInt(6)%></TD>
<TD><%=rs.getInt(7)%></TD>
<TD><%=rs.getInt(8)%></TD>

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