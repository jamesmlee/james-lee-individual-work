<%-- 
    Document   : result
    Created on : Oct 3, 2017, 10:56:16 AM
    Author     : James
--%>
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
            Cost of materials: ${costMaterials}
            <br><br>
            Cost of labor: ${costLabor}
            <br><br>
            Total cost: ${costTotal}
            <br><br>
            Time required: ${timeRequired}
        </p>
        <p>
            <a href="index.jsp">Again</a>
        </p>
    </body>
</html>
