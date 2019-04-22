<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check in Book</title>

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

input[type=submit] {
    background-color:#0073e6;
    padding:12px 20px;
    border:none;
    border-radius:5px;
    color:white;
    cursor:pointer;
</style>
			
</head>
<body>
	<ul>
  <li> <a href="index.html" >Home</a></li>
  <li> <a href="FineController?action=viewfine" >Fine Management</a></li>
  <li> <a href="addBorrower.jsp">Add Borrower</a></li>
  
</ul>
	<br/>
	
	    <table>
	    		<tr>
	    			<th>Loan ID</th>
		            <th>ISBN</th>
		            <th>Title</th>
		            <th colspan="2">Borrower Name</th>
		            <th>Checked Out Date</th>
		            <th>Due Date</th>
		            <th>Action</th>
	            </tr>
	        <c:forEach items="${checkinBooks}" var="book">
	            
	            <tr>
	            	<td>${book.loan_id }</td>
	                <td>${book.isbn }</td>
	                <td>${book.title }</td>
	                <td>${book.fname }</td>
	                <td>${book.lname }</td>
	                <td>${book.date_out }</td>
	                <td>${book.due_date }</td>
               		<td>
			            <form action="BookLoansController" method="post">
			            <input type="hidden" name="action" value="checkin">
			             <input type="hidden" name="loan_id" value="<c:out value="${book.loan_id}"/>">
	 						<input type="submit" value="Check in">
			            </form>
			        </td>
	    		</tr>
	        </c:forEach>
	    </table>
</body>
</html>