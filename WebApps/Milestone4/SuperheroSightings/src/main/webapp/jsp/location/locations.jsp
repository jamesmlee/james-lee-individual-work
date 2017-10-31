<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO - Locations Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/mapstyling.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container" style="margin-bottom: 5px;">
            <h1>HERO - Super Person Locations</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> <!-- not sure about page names yet -->
                        <a href="${pageContext.request.contextPath}/">
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
                    <li role="presentation" class="active">
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
                <div class="col-md-9"><!-- Begin Main Left Panel -->
                    <h2>Selected Location: <c:out value="${lvm.location.name}"/> </h2>
                    <hr>
                    <div class="col-md-4">
                        <h4>Information</h4>
                        <div style = "border: 1px black solid">
                            <!-- this is where the pic and google map go -->
                            <img src="${pageContext.request.contextPath}/images/locations/${lvm.location.locationId}.jpg" width="200px"/>
                            <br>
                            <p class="left-padding-plus" style="margin-bottom:0"><c:out value="${lvm.address.street}"/></p>
                            <p class="left-padding-plus"><c:out value="${lvm.address.city}"/>, <c:out value="${lvm.address.state}"/> <c:out value="${lvm.address.zipcode}"/></p>
                            <p class="left-padding-plus">Coordinates: <span id="lat">${lvm.location.latitude}</span>, <span id="long">${lvm.location.longitude}</span></p>
                            <div id="map"></div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <h4>Description</h4>
                            <textarea rows="25" cols="30" readonly><c:out value="${lvm.location.description}"/></textarea>
                        
                    </div>
                    <div class="col-md-4">
                        <h5>Super Persons Seen Here</h5>
                        <div><!-- should be a table -->
                            <table class="table table-hover">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="35%">Name</th>
                                    <th width="60%">Image</th>
                                </tr>
                                <tbody> <!-- will this throw a null pointer if the initial page load doesn't have svm on the model?  if so, just put an empty svm on the model in the initial page load.... -->
                                    <c:forEach 
                                        items="${lvm.superPersons}" 
                                        var="currentSuperPerson" 
                                        varStatus="loop">
                                        <tr> <!-- should include links to the locations page, sightings page, and superperson page -->
                                            <td><c:out value="${loop.count}"/></td>
                                            <td><c:out value="${currentSuperPerson.name}"/></td><!-- name -->                                           
                                            <td><img src="${pageContext.request.contextPath}/images/superpersons/${currentSuperPerson.superPersonId}.jpg" width="60px" 
                                                         style="border: solid black;"/></td>                                          
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- End main right column -->
                <div class="col-md-3"> <!-- begin main right col -->
                    <sf:form class="form-horizontal" role="form" modelAttribute="button"
                             action="${pageContext.request.contextPath}/location/displayCreateLocationPage" method="POST">                             
                        <input type="submit" class="btn btn-default" name="button" value="Add Location" />
                    </sf:form>
                    <h2>Locations</h2>
                    <div>
                        <table id="locationTable" class="table table-hover">
                            <tr>
                                <th width="5%">#</th>
                                <th width="50%">Name</th>
                                <th width="45%"></th>
                            </tr>
                            <tbody>
                                <c:forEach 
                                    items="${lvmList}" 
                                    var="currentLVM" 
                                    varStatus="loop">
                                    <tr> 
                                        <td><a href="chooseLocation?locationClicked=${currentLVM.location.locationId}"><c:out value="${loop.count}"/></a></td>
                                        <td><c:out value="${currentLVM.location.name}"/></td>                                                                                    
                                        <td><a href="REQUESTMAPPING">Edit</a><br/><a href="REQUESTMAPPING">Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> <!-- End main right col -->
            </div> <!-- End row div -->
            <!-- Main Page Content Stop -->                 
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
            function initMap() {
                var lati = "<c:out value='${lvm.location.latitude}'/>";
                var long = "<c:out value='${lvm.location.longitude}'/>";
                var map = new google.maps.Map(document.getElementById("map"),
                        {zoom: 15, center: new google.maps.LatLng(lati, long)});
                var marker = new google.maps.Marker({
                    position: {lat: <c:out value='${lvm.location.latitude}'/>, lng: <c:out value='${lvm.location.longitude}'/>},
                    map: map
                });
            }
        </script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC84zAyiffzxBzIApQHoin_5xQUp83eHSo&callback=initMap"></script>
    </body>
</html>