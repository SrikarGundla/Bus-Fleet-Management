<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new driver</title>
</head>
<body>
<h1>New Driver details</h1>
<br>
<form action="Add_DriverServlet" method="post">

			<table style="with: 50%">
			
				<tr>
					<td>Enter Driver Id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				
				<tr>
					<td>Enter Driver Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				
				<tr>
					<td>Enter Driver password</td>
					<td><input type="text" name="password" /></td>
				</tr>
				
				<tr>
					<td>Do he work on Sunday? (Enter 0 or 1)</td>
					<td><input type="text" name="Sunday" /></td>
				</tr>
				
				<tr>
					<td>Do he work on Monday? (Enter 0 or 1)</td>
					<td><input type="text" name="Monday" /></td>
				</tr>
				
				<tr>
					<td>Do he work on Tuesday? (Enter 0 or 1)</td>
					<td><input type="text" name="Tuesday" /></td>
				</tr>
				
				<tr>
					<td>Do he work on Wednesday? (Enter 0 or 1)</td>
					<td><input type="text" name="Wednesday" /></td>
				</tr>
				
				<tr>
					<td>Do he work on Thursday? (Enter 0 or 1)</td>
					<td><input type="text" name="Thursday" /></td>
				</tr>
				
				<tr>
					<td>Do he work on Friday? (Enter 0 or 1)</td>
					<td><input type="text" name="Friday" /></td>
				</tr>
				
				<tr>
					<td>Do he work on Saturday? (Enter 0 or 1)</td>
					<td><input type="text" name="Saturday" /></td>
				</tr>
				
				<tr>
					<td>Enter his current city ID</td>
					<td><input type="text" name="city_id" /></td>
				</tr>

			</table>
			
<br>
			
			<input type="submit" value="Submit" /></form>
			
</body>
</html>