<%-- 
    Document   : result
    Created on : Oct 3, 2017, 2:39:05 PM
    Author     : James
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
        <h1>Results</h1>
        <!-- turn this into a loop that prints out the info for each year -->        
        <p>
            End of year #: 
            <br><br>
            Balance @beginning of year: 
            <br><br>
            Interest earned for the year: 
            <br><br>
            Balance @end of year: 
        </p>
        <p>
            <a href="index.jsp">Again</a>
        </p>
    </body>
</html>


<!--
<c:forEach items="${periodList}" var="period">
    <b><c:out value=" Year: ${period.year}"/></b><br/>
    <c:out value=" 
           Balance Beginning Year: $${period.balanceBeginningYear}
           Interest Accrued During Year: $${period.interestAccrued}
           Balance End Year: $${period.currentBalance} "/><br/><br/>                       
</c:forEach>
-->