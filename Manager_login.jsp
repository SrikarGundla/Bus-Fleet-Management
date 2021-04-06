<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager web page</title>
</head>
<body>
<h1>Manager needs to enter Login Credentials</h1>
<h1></h1>
<form action="Manager_loginServlet" method="post">

			<table style="with: 50%">
				<tr>
					<td>Login Id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="text" name="password" /></td>
				</tr>

			</table>
			<input type="submit" value="Submit" /></form>
</body>
</html>