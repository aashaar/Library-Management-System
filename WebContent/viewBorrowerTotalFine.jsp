<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Borrower Fines</title>
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
</style>
<script>

if(${finePaid == true}){
alert("Fine paid successfully !");
}
else if(${finePaid == false}){
alert("There was some error in paying the fine. Please try again.");
} 
else if(${finesRefreshed == true}){
	  alert("Fines refreshed in the database");
	}
			
</script>
		</head>
<body>
<br/>

	<ul>
  <li> <a href="index.html" >Home</a></li>
  <li> <a href="FineController?action=refreshFine" >Refresh fines</a></li>
  <li> <a href="FineController?action=searchforpayfine" >Pay fines</a></li>
  <li> <a href="checkin.jsp">Check In Books</a></li>
</ul>

<br/>
	<table>
	    		<tr>
		            
		            <th>Card ID</th>
		            <th colspan ="2">Borrower</th>
		            <th>Current Fine Amount($)</th>
		            
		            
	            </tr>
	        <c:forEach items="${borrowerTotalFines}" var="fine">
	            
	            <tr>
	                
	                <td>${fine.card_id }</td>
	                <td>${fine.fname }</td>
	                <td>${fine.lname }</td>
	                <td>${fine.fine_amt }</td>
	                </td>
	            </tr>
	        </c:forEach>
	    </table>
</body>
</html>