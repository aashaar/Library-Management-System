<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pay Fine</title>
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
			
			<!-- Style for table -->
			<style>
		ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
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
    background-color:#ff8c1a;
    padding:12px 20px;
    border:none;
    border-radius:5px;
    color:white;
    cursor:pointer;
</style>
			
</head>
<body>
	<br/>
	<ul>
  <li> <a href="index.html" >Home</a></li>
  <li> <a href="FineController?action=refreshFine" >Refresh fines</a></li>
  <li> <a href="FineController?action=viewfine" >View fines</a></li>
  
</ul>
	<br/>
	<table>
	    		<tr>
		            <th>Loan ID</th>
		            <th>ISBN</th>
		            <th>Card ID</th>
		            <th colspan ="2">Borrower</th>
		            <th>Check out</th>
		            <th>Due Date</th>
		            <th>Check in</th>
		            <th>Fine Amount($)</th>
		            <th>Action</th>
		            
	            </tr>
	        <c:forEach items="${fines}" var="fine">
	            
	            <tr>
	                <td>${fine.loan_id}</td>
	                <td>${fine.isbn }</td>
	                <td>${fine.card_id }</td>
	                <td>${fine.fname }</td>
	                <td>${fine.lname }</td>
	                <td>${fine.date_out }</td>
	                <td>${fine.due_date }</td>
	                <td>${fine.date_in }</td>
	                <td>${fine.fine_amt }</td>
	                <td>
			            <form action="FineController" method="get">
			            <input type="hidden" name="action" value="payfine">
			             <input type="hidden" name="loan_id" value="<c:out value="${fine.loan_id}"/>">
	 						<input type="submit" value="Pay Fine">
			            </form>
			        </td>
	            </tr>
	        </c:forEach>
	    </table>
</body>
</html>