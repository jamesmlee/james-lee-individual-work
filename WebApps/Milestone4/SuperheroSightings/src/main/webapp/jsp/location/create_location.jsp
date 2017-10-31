<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Location</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Add Location</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-xs-9 col-xs-offset-3">
                </div>
            </div>
            <div class="row">
                <sf:form id="createSightingForm" class="form-horizontal" commandName="clcm"
                         action="${pageContext.request.contextPath}/sighting/createSighting" method="POST">
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Name</label>
                        <div class="col-xs-5">
                            <sf:errors path="name" cssclass="error"></sf:errors>
                                <input type="text" name="name" id="name" class="form-control" rows="5"
                                       placeholder="Enter Location Name" path="name"></input>         
                            </div>
                        </div>   
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Street Address</label>
                            <div class="col-xs-5">
                            <sf:errors path="street" cssclass="error"></sf:errors>
                                <input type="text" name="street" id="street" class="form-control" rows="5"
                                       placeholder="Enter Street" path="street"></input>         
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">City</label>
                            <div class="col-xs-5">
                            <sf:errors path="city" cssclass="error"></sf:errors>
                                <input type="text" name="city" id="city" class="form-control" rows="5"
                                       placeholder="Enter City" path="city"></input>         
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">State</label>
                            <div class="col-xs-5">
                            <sf:errors path="state" cssclass="error"></sf:errors>
                                <input type="text" name="state" id="name" class="form-control" rows="5"
                                       placeholder="Enter State (XX)" path="state"></input>         
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Zipcode</label>
                            <div class="col-xs-5">
                            <sf:errors path="zipcode" cssclass="error"></sf:errors>
                                <input type="text" name="zipcode" id="name" class="form-control" rows="5"
                                       placeholder="Enter Zipcode" path="zipcode"></input>         
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
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Latitude</label>
                            <div class="col-xs-5">
                            <sf:errors path="latitude" cssclass="error"></sf:errors>
                                <input type="text" name="latitude" id="name" class="form-control" rows="5"
                                       placeholder="Enter Latitude" path="latitude"></input>         
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Longitude</label>
                            <div class="col-xs-5">
                            <sf:errors path="longitude" cssclass="error"></sf:errors>
                                <input type="text" name="longitude" id="name" class="form-control" rows="5"
                                       placeholder="Enter Longitude" path="longitude"></input>         
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-5 col-xs-offset-3">
                            <input type="hidden" name="nameHidden" value="">
                            <input type="hidden" name="addressHidden" value="">
                            <input type="hidden" name="latitudeHidden" value="">
                            <input type="hidden" name="longitudeHidden" value="">
                            <button type="submit" class="btn btn-default" id="btnCreateSighting">Add Location</button>
                            <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/location/locations" formmethod="GET">Cancel</button>
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