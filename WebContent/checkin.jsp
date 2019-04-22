<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Book</title>

<script>
if(${checkin == true}){
	  alert("Book checked in successfully !");
	}
else if(${checkin == false}){
	  alert("Checkin failed. Please try again.");
	}	
</script>
<style>
input[type=text]{
    width:50%;
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
</head>
<body>

<ul>
  <li> <a href="index.html" >Home</a></li>
  <li> <a href="FineController?action=viewfine" >Fine Management</a></li>
  <li> <a href="addBorrower.jsp">Add Borrower</a></li>
  
</ul>

<form action="BookLoansController" method="get">
<h3>Enter keywords to search book for check in(book name/id or borrower name/id)</h3>
  <input type = "text" name = "checkinSearchString"><br/><br/>
  <input type="hidden" name="action" value="checkinSearch">
  <input type="submit" value="Search">
</form>
</body>
</html>