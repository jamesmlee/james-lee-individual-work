<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>

    <body>
        <div class="container">
            <h1>Super Person Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"> <!-- not sure about page names yet -->
                        <a href="${pageContext.request.contextPath}/home">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/superperson/superpersons">
                            Super Persons
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/sighting/sightings">
                            Sightings
                        </a> 
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/organization/organizations">
                            Organizations
                        </a> 
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/location/locations">
                            Locations
                        </a> 
                    </li>
                </ul>    
            </div>
            <!-- Main Page Content Start -->

            <ul class="list-group" id="errorMessages"></ul>
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>HERO</h2>
                    <p>Superhero and supervillain sightings have been increasing at an alarming rate. Supervillains have brought about chaos, fear, and destruction. Superheroes have been doing their part to stop them, while also serving the greater good every day while working with armed forces and first responders. We live in a new world where a heightened sense of awareness is necessary to stay safe. </p>
                    <p>Please use this site to keep track of super person sightings. You can look over information on super persons, sightings, super person organizations, and locations. However, we need your help! Please report information related to super persons and sightings whenever you can. The more information we have, the better. </p>
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the search form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-6">
                    <h2>Most Recent Sightings</h2>
                    <div><!-- should be a table -->
                        <table id="sightingTable" class="table table-hover">
                            <tr>
                                <th width="5%">#</th>
                                <th width="25%">Date</th>
                                <th width="35%">Super Persons</th>
                                <th width="35%">Location</th>
                            </tr>
                            <tbody>
                                <c:forEach 
                                    items="${svmList}" 
                                    var="currentSVM" 
                                    varStatus="loop">
                                    <tr> <!-- these would be like currentSighting[0], currentSighting[1] for each c:out -->
                                        <td><c:out value="${loop.count}"/></td>
                                        <td><c:out value="${currentSVM.sighting.date}"/></td>                                           
                                        <td>
                                            <c:forEach 
                                                items = "${currentSVM.superPersons}"
                                                var = "currentSuperPerson"
                                                varStatus = "innerLoop">
                                                <c:out value="${innerLoop.count}. ${currentSuperPerson.name}" /><br>
                                            </c:forEach>
                                        </td>                                         

                                        <td><c:out value="${currentSVM.location.name}"/></td>        

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> <!-- End col div -->

            </div> <!-- End row div -->
            <!-- Main Page Content Stop -->                 
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>