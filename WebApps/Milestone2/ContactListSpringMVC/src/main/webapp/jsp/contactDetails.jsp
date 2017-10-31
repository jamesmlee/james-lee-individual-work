<%-- 
    Document   : contactDetails
    Created on : Oct 5, 2017, 3:03:04 PM
    Author     : James
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Details</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
    </head>
    <body>
        <div class="container">
            <h1>Contact List Spring MVC</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a></li>
                </ul>    
            </div>
            <h2>Contact Information</h2>
            <p>Name: <c:out value="${selectedContact.firstName}" /> <c:out value="${selectedContact.lastName}" />
            </p>
            <p>Company: <c:out value="${selectedContact.company}" /> </p>
            <p>Email: <c:out value="${selectedContact.email}" /> </p>
            <p>Phone: <c:out value="${selectedContact.phone}" /> </p>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
