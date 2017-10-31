<%-- 
    Document   : index
    Created on : Oct 3, 2017, 2:38:42 PM
    Author     : James
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interest Calculator</title>
    </head>
    <body>
        <h1>Interest Calculator</h1>
        <p>
        <form method="POST" action="CalculatorServlet">
            <p>Annual interest rate: </p>
            <input type="text" name="annualRate">
            <p>Amount to invest: </p>
            <input type="text" name="principal">
            <p># years to invest for: </p>
            <input type="text" name="numYears">
            <input type="submit" value="Calculate Interest">
        </form>
        <p>
    </body>
</html>
