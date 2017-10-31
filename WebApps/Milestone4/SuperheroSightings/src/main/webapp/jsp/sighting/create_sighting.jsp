<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Add Sighting</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-xs-9 col-xs-offset-3">
                </div>
            </div>
            <div class="row">
                <sf:form id="createLocationForm" class="form-horizontal" commandName="scm"
                         action="${pageContext.request.contextPath}/sighting/createSighting" method="POST">
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Date </label>
                        <div class="col-xs-5">
                            <sf:errors path="date" cssclass="error"></sf:errors>
                                <input id="date" name="date" type="date" path="date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Location </label>
                            <div class="col-xs-5">
                                <select name="locationId" id="locationId" form="createSightingForm" path="locationId">
                                <c:forEach
                                    items="${locations}" 
                                    var="currentLocation" 
                                    varStatus="loop">
                                    <option value="${currentLocation.locationId}">${currentLocation.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Super Persons </label>
                        <sf:errors path="superPersons" cssclass="error"></sf:errors>
                            <div class="col-xs-5">
                                <select multiple name="superPersons" form="createSightingForm" path="superPersons">
                                <c:forEach
                                    items="${superPersons}" 
                                    var="currentSuperPerson" 
                                    varStatus="loop">
                                    <option value="${currentSuperPerson.superPersonId}">${currentSuperPerson.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Description</label>
                        <div class="col-xs-5">
                            <sf:errors path="description" cssclass="error"></sf:errors>
                                <textarea name="description" id="description" class="form-control" rows="5"
                                          placeholder="Enter Description" path="description"></textarea>         
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-5 col-xs-offset-3">
                        <input type="hidden" name="nameHidden" value="">
                        <input type="hidden" name="addressHidden" value="">
                        <input type="hidden" name="latitudeHidden" value="">
                        <input type="hidden" name="longitudeHidden" value="">
                        <button type="submit" class="btn btn-default" id="btnCreateSighting">Report Sighting</button>
                        <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/superperson/superpersons" formmethod="GET">Cancel</button>
                </div>
            </div>
        </sf:form>
    </div>
    <!-- Main Page Content Start -->
    <!-- Main Page Content Stop -->
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>