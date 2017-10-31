<%-- 
    Document   : result
    Created on : Oct 2, 2017, 10:55:40 PM
    Author     : James
--%>
<!-- add directive for taglib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h1>Result</h1>
        <p>
            You asked to factor ${numberToFactor}
        </p>
        <p>
            <!-- var is the name of variable for each thing to pull out; items is the collection to iterate through-->            
            <c:forEach var="currentFactor" items="${factors}">
                <!-- space b/t the factors -->
                <c:out value="${currentFactor} " />
            </c:forEach>
        </p>
        <!-- perfect -->        
        <p>
            <c:choose>
                <c:when test="${isPerfect}">
                    <c:out value="The number is perfect" />
                </c:when>
                <c:otherwise>
                    <c:out value="The number is not perfect" />
                </c:otherwise>
            </c:choose>
        </p>
        <!-- prime -->        
        <p>
            <c:choose>
                <c:when test="${isPrime}">
                    <c:out value="The number is prime" />
                </c:when>
                <c:otherwise>
                    <c:out value="The number is not prime" />
                </c:otherwise>
            </c:choose>
        </p>
        <!-- link back -->        
        <p>            
            <a href="index.jsp">Factor Another One</a>
        </p>
    </body>
</html>
