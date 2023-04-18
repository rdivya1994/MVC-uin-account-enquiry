<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="bean.UinBean" %>

<%ArrayList records = (ArrayList)request.getAttribute("records"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
</head>
<body>
<h1 align="center">Loan Enquiry</h1>

<% UinBean rec = (UinBean)records.get(0); %>
<div style="margin-left:500px">
Name: <%= rec.getName() %><br>
UIN: <%= rec.getUin() %><br><br>

<form method="get" action="<%=request.getContextPath()%>/enquiry.jsp" name="myForm" >
<table>
<% for (int recordCount =0;recordCount<records.size();recordCount++){%>
<% UinBean res = (UinBean)records.get(recordCount); %>

  <tr>
    <th>Account:</th>
    <td><%= res.getAccount() %></td>
  </tr>
  <tr>
    <th>Address:</th>
    <td><%= res.getAddress() %></td>
  </tr>
  <tr>
    <th>HouseHold Information:</th>
    <td></td>
  </tr>
  <tr>
    <th>Record:</th>
    </tr>
    <tr>    
    <td>UIN:<%= res.getUin()%></td>    
    <td>Name:<%= res.getName()%></td>    
    <td>Age:<%= res.getAge()%></td>
    </tr>
 

   <tr>
    <th>>Loan Information:</th>
  </tr>
  <tr>
    <th>Outstanding Loan:</th>
    <td><%= res.getOutstandingloan() %></td>
  </tr>
  <tr>
    <th>Last Payment Date:</th>
    <td><%= res.getLastpaymentdate() %></td>
  </tr>
  <tr>
    <th>Repayment Period</th>
    <td><%= res.getRepayperiodinmonths() %></td>
  </tr>
<%} %>
</table>
<br><br>	
<input type="submit" value="Back" />
</form>
</div>

</div>
</body>
</html>