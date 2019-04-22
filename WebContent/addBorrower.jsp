<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Borrower</title>

<script>
if(${ssn_duplicate == true}){
	  alert("A borrower with given SSN already exists. Please try again.");
	}
else if(${success == true}){
	  alert("Borrower added succesfully!");
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
ul 
{
	background-color: #333;
    list-style-type:none;
    padding: 0;
    margin: 0;
    overflow: hidden;
    
}


li a 
{
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li 
{
    float: left;
}

li a:hover 
{
    background-color: #111;
}
</style>


</head>
<body>

<ul>
  <li> <a href="index.html" >Home</a></li>
  <li> <a href="checkin.jsp">Check In Books</a></li>
  <li> <a href="FineController?action=viewfine" >Fine Management</a></li>
</ul>
<br/>

<form method="POST" action="BorrowerController?action=add" name="addBorrower">

First Name <br/>
<input type="text" name="fname" value ="<c:out value="${Borrower.fname}"/>" placeholder = "Your first name..."  required/>
<br/>
Last Name <br/>
<input type="text" name="lname" value ="<c:out value="${Borrower.lname}"/>" placeholder = "Your last name..."  required/>
<br/>
SSN Number <br/>
<input type="text" name="ssn" value ="<c:out value="${Borrower.ssn}"/>"  pattern="[0-9]{9}" title="9 digit SSN" placeholder = "Your 9 digit SSN number" required/>
<br/>
Email <br/>
<input type="text" name="email" value ="<c:out value="${Borrower.email}"/>" pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="Example: abc@xyz.com" placeholder = "Your email address..." required/>
<br/>
Address <br/>
<input type="text" name="address" value ="<c:out value="${Borrower.address}"/>" placeholder = "Your postal address..." required/>
<br/>
Phone Number <br/>
<input type="text" name="phone" value ="<c:out value="${Borrower.phone}"/>" pattern="^[0-9]{3}-[0-9]{3}-[0-9]{4}$" title="Example : 987-654-3210" placeholder = "Your phone number..." required/>
<br/>

<input type="submit" value="Add Borrower" />

</form>



</body>
</html>