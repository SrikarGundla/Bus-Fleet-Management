<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new City</title>
</head>
<body>
<h1>New City details</h1>
<br>
<form action="Add_CityServlet" method="post">

			<table style="with: 50%">
			
				<tr>
					<td>Enter City Id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				
				<tr>
					<td>Enter City Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				
				<tr>
					<td>Enter State name</td>
					<td><input type="text" name="state" /></td>
				</tr>

			</table>
			
<br>
			
			<input type="submit" value="Submit" /></form>
			
</body>
</html>