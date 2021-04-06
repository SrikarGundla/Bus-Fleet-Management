<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.sql.*" %>
<%@ page import="java.io.IOException"%>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.*" %>

 
<html>
<head>
<title>Driver Trips</title>
</head>
<body>
<h2>Your Trips:</h2>
<%

try {
	
	Connection con = null;
		String url = "jdbc:postgresql://localhost:5432/dbms_project";
		String username = "dbms_project";
		String password = "1234";
	
	Class.forName("org.postgresql.Driver");
	con = DriverManager.getConnection(url, username, password);
		System.out.println("Printing connection object "+con);
	
	
	PreparedStatement st = con.prepareStatement("select * from trip_details");
 		
	ResultSet rset = st.executeQuery();
	

%>

<TABLE cellpadding="15" border="1" style="background-color: #fecabf;">


<TR>

<TD>Trip Number</TD>
<TD>Bus ID</TD>
<TD>From City ID</TD>
<TD>To City ID</TD>
<TD>Trip Date</TD>
<TD>Route</TD>
<TD>Bus Name</TD>
<TD>Bus Number</TD>
<TD>Bus Timings</TD>

</TR>





<%

int num = 0 ;

while (rset.next()) {
	
	if(rset.getInt(5) == (Integer) request.getAttribute("driver_id"))  {
		
		num += 1 ;

%>

<TR>

<TD><%=num%></TD>
<TD><%=rset.getInt(2)%></TD>
<TD><%=rset.getInt(3)%></TD>
<TD><%=rset.getInt(4)%></TD>
<TD><%=rset.getString(6)%></TD>
<TD><%=rset.getString(7)%></TD>
<TD><%=rset.getString(8)%></TD>
<TD><%=rset.getString(9)%></TD>
<TD><%=rset.getString(10)%></TD>

</TR>

<% } %>

<% } %>

<%

rset.close();
st.close();
con.close();
} catch (Exception ex) {
	
%>

<font>
<font size="+3" color="red"></b>

<%
out.println("Unable to connect to database.");
}

%>
</TABLE>
</font>
</body>
</html>