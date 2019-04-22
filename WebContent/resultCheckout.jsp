<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>

<script>
if(${checkedout == true}){
	  alert("Book successfully checked out to the borrower");
	}
else if(${checkedout == false}){
	  alert("Sorry, Check out failed. Book is already checked out.");
	}
else if(${loanQuotaExceeded == true}){
	  alert("Sorry, Check out failed. Borrower already has 3 active books loans.");
	}
else if(${borrowerNotExist == true}){
	alert("Sorry, Check out failed. Borrower with given Card ID does not exist in the system.");
}
</script>

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
  <li> <a href="checkin.jsp">Check In Books</a></li>
  <li> <a href="FineController?action=viewfine" >Fine Management</a></li>
  <li> <a href="addBorrower.jsp">Add Borrower</a></li>
  
</ul>
<br/>

<h3>Checkout Policy : </h3>

<h4>
A borrower is allowed a maximum of 3 loans at a time.
<br/>A book should be returned within 14 days of checking out.
<br/> After 14 days, the borrower will be charged a penalty of $0.25 per day per book. 
</h4>


</body>
</html>