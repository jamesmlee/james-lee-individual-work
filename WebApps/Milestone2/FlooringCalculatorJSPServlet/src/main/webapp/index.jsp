<%-- 
    Document   : index
    Created on : Oct 3, 2017, 10:53:47 AM
    Author     : James
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator</title>
    </head>
    <body>
        <h1>Flooring Calculator</h1>
        <p>
        <form method="POST" action="FlooringServlet">
            <p>Width (ft): </p>
            <input type="text" name="width">
            <p>Length (ft): </p>
            <input type="text" name="length">
            <p>Cost (per SqFt): </p>
            <input type="text" name="costPerSqFt">
            <input type="submit" value="Make Calculations">
        </form>
        <p>
    </body>
</html>
