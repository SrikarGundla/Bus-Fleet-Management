<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Allocate Bus</title>
</head>
<body>
<h1>Allocate trip to a driver</h1>
<br>
<form action="Allocate_BusServlet" method="post">

			<table style="with: 50%">
			
				<tr>
					<td>Enter Driver Id</td>
					<td><input type="text" name="driver_id" /></td>
				</tr>
				
				<tr>
					<td>Enter Bus Id</td>
					<td><input type="text" name="bus_id" /></td>
				</tr>

			</table>
			
<br>
			
			<input type="submit" value="Submit" /></form>
			
</body>
</html>