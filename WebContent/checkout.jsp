<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
<style>
input[type=text]{
    width:20%;
    padding:13px;
    box-sizing:border-box;
    border:1px solid #ccc;
    border-radius:5px;
    margin-top:5px;
    margin-bottom:15px;
    resize:vertical;
}

input[type=submit] {
    background-color:#0073e6;
    padding:12px 20px;
    border:none;
    border-radius:5px;
    color:white;
    cursor:pointer;
}

input[type=submit]:hover {
    background-color:#0059b3;
}

.container {
    
    background-color:#f2f2f2;
    border-radius:5px;
    padding:20px;
}
</style>

<style>
ul {
    list-style-type:none;
    padding: 0;
    margin: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}
</style>


<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</style>

</head>
<body>

<ul>
  <li> <a href="index.html" >Home</a></li>
  <li> <a href="checkin.jsp">Check In Books</a></li>
  <li> <a href="FineController?action=viewfine" >Fine Management</a></li>
  <li> <a href="addBorrower.jsp">Add Borrower</a></li>
  
</ul>
<br/>
<br/>
<%-- <input type="hidden" name="isbn" value="<c:out value="${param.isbn}" />"> --%>
<table>
<tr>
	<th><h2></>Title</h2></th>
    <td><h2>${book.title }</h2></td>
</tr>

<tr>
<th><h3>Authors</h3></th>
<td><h3>${book.author }</h3></td>
</tr>
<tr>
	<th>ISBN</th>
	<td>${book.isbn }</td>
</tr>


</table>
<br/>
<br/>
<form action="BorrowerController" method="get">
<input type="hidden" name="action" value="<c:out value="checkout"/>">
Borrower card ID
<br/> 
<input type="text" name= "card_id" value"<c:out value="${Borrower.card_id}"/> " pattern="[0-9]{1,5}" title="Please enter digits only" placeholder="Enter Borrower Card ID number..."  required/>
<input type="hidden" name="isbn" value="<c:out value="${book.isbn}"/>">
<input type="submit" value="Submit">
</form>

</body>
</html>