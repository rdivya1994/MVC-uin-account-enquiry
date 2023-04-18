<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.UinBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%ArrayList records = (ArrayList)request.getAttribute("records"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UIN</title>
</head>
<body>
<div>
<h1 align="center">Loan Enquiry</h1>
<% UinBean rec = (UinBean)records.get(0); %>
<div style="margin-left:500px">
Name: <%= rec.getName() %><br>
UIN: <%= rec.getUin() %><br><br>

<form method="get" action="account" name="myForm" >
<table>
<% for (int recordCount =0;recordCount<records.size();recordCount++){%>
<% UinBean res = (UinBean)records.get(recordCount); %>

 <tr>
    <th>
     <input type="radio" name="account" value="<%= res.getAccount() %>"/>Account:</th>
    <td><%= res.getAccount() %></td>
  </tr>
  <tr>
    <th>Address:</th>
    <td><%= res.getAddress() %></td>
  </tr>
<%} %>
</table>
<br><br>
<input type="submit" value="View" class="btn btn-info" />
<%--For displaying Previous link except for the 1st page --%>
  <c:if test="${currentPage != 1}">
      <td><a class="btn btn-info" href="Enquiry?item=${item}&num=${num}&page=${currentPage - 1}">Previous</a></td>
  </c:if>
    <%--For displaying Next link --%>
  
  <c:if test="${currentPage lt noOfPages}">
      <td><a class="btn btn-info" href="Enquiry?item=${item}&num=${num}&page=${currentPage + 1}">Next</a></td>
  </c:if>
   <%--For displaying Page numbers. The when condition does not display
              a link for the current page--%>
  <br><br>
  <table border="1" cellpadding="5" cellspacing="5">
      <tr>
          <c:forEach begin="1" end="${noOfPages}" var="i">
              <c:choose>
                  <c:when test="${currentPage eq i}">
                      <td class="btn btn-info" style="background-color: white!important;color: black!important">${i}</td>
                  </c:when>
                  <c:otherwise>
                      <td><a class="btn btn-info"  href="Enquiry?item=${item}&num=${num}&page=${i}">${i}</a></td>
                  </c:otherwise>
              </c:choose>
          </c:forEach>
      </tr>
  </table>
  

</form>
</div>

</div>
</body>
</html>