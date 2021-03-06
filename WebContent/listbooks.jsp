<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Search Results</title>
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
    background-color:#0073e6;
    padding:12px 20px;
    border:none;
    border-radius:5px;
    color:white;
    cursor:pointer;
</style>
		<script>
if(${results == false}){
	  alert("No results found for given search");
	}

</script>				
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
	    <table>
	    		<tr>
		            <th>ISBN</th>
		            <th>Title</th>
		            <th>Authors</th>
		            <th>Action</th>
	            </tr>
	        <c:forEach items="${books}" var="book">
	            
	            <tr>
	                <td>${book.isbn }</td>
	                <td>${book.title }</td>
	                <td>${book.author }</td>
	              	<td>
	              	<c:choose>
	         
				         <c:when test = "${book.available != 'false'}">
				         
				            <form action="BookController" method="get">
				            <input type="hidden" name="action" value="getBookByIsbn">
				             <input type="hidden" name="isbn" value="<c:out value="${book.isbn}"/>">
	  						<input type="submit" value="Checkout">
				            </form>
				         </c:when>
				         
				         
				         <c:when test = "${book.available == 'false'}">
				           Checked out
				         </c:when>
				         
				         <c:otherwise>
				            ------
				         </c:otherwise>
				      </c:choose>
	              	  </td>	
	            </tr>
	        </c:forEach>
	    </table>
	</body>
</html>