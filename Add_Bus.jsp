<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new Bus</title>
</head>
<body>
<h1>New Bus details</h1>
<br>
<form action="Add_BusServlet" method="post">

			<table style="with: 50%">
			
				<tr>
					<td>Enter Bus Id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				
				<tr>
					<td>Enter Bus Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				
				<tr>
					<td>Enter From/To Id</td>
					<td><input type="text" name="ft" /></td>
				</tr>
				
				<tr>
					<td>Enter To/From Id</td>
					<td><input type="text" name="tf" /></td>
				</tr>
				
				<tr>
					<td>Enter Route</td>
					<td><input type="text" name="route" /></td>
				</tr>
				
				<tr>
					<td>Enter Bus current city ID</td>
					<td><input type="text" name="city_id" /></td>
				</tr>
				
				<tr>
					<td>Enter Bus Number</td>
					<td><input type="text" name="numb" /></td>
				</tr>
				
				<tr>
					<td>Enter Bus Timings</td>
					<td><input type="text" name="timi" /></td>
				</tr>

			</table>
			
<br>
			
			<input type="submit" value="Submit" /></form>
			
</body>
</html>