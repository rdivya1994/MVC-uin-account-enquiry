<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Enquiry</title>
</head>
<body>
<div align="center">
  <h1>Loan Enquiry</h1>
  <form method="get" action="Enquiry" name="myForm">
  <select name="item">
    <option value="UIN">UIN</option>
    <option value="Account">Account</option>
  </select>
  <input type="text" name="num">
  <input type="submit" value="Search">
</form>
</div>
</body>
</html>