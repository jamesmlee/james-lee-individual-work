<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html> 
<html>
    <head>
        <title>Register Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Register Organization</h1>
                    <p>NOTE: If the new organization also has a new location, you must <a href="${pageContext.request.contextPath}/location/displayCreateLocationPage">register the location first</a></p>
                </div>
            </div>
            <hr/>
            <div class="row"> <!-- form wide errors? -->

            </div>
            <div class="row">
                <sf:form id="createOrganizationForm" class="form-horizontal" commandName="ocm"
                         action="${pageContext.request.contextPath}/organization/createOrganization" method="POST">

                    <!-- text form for NAME -->
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Name </label>
                        <div class="col-xs-5">
                            
                                <input id="name" name="name" type="text" path="name" placeholder="Enter name"/><span><sf:errors path="name" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- text form for DESCRIPTION -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Description</label>
                            <div class="col-xs-5">
                            <sf:errors path="description" cssclass="error"></sf:errors>
                                <textarea name="description" id="description" class="form-control" rows="5"
                                          placeholder="Enter Description" path="description"></textarea>
                            </div>
                        </div>

                        <!-- tel form for PHONE -->
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Telephone </label>
                            <div class="col-xs-5">
                                
                                <input id="phone" name="phone" type="tel" path="phone" placeholder="(555) 555-5555"/>
                                <p><sf:errors path="phone" cssclass="error"></sf:errors></p>
                            </div>
                        </div>

                        <!-- radio form for ISGOOD -->
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Reputation </label>
                            <div class="col-xs-5">
                            <sf:errors path="isGood" cssclass="error"></sf:errors>
                                <div class="input-group">
                                    <!-- 
                                    
                                    not sure how this should be set up
                                        each should have a path, or just the input group?
                                        if i want to have a null on the OCM if they
                                        choose Unknown, can I leave value and path off?
                                    -->
                            <input name="isGood" type="radio" path="isGood" value="good"/> Good <br>
                            <input name="isGood" type="radio" path="isGood"value = "evil"/> Evil <br>
                                
                                </div>
                            </div>
                        </div>


                        <!-- select form for LOCATION -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Location</label>
                            <div class="col-xs-5">
                                <select name="locationId" id="locationId" form="createOrganizationForm" path="locationId">
                                <c:forEach
                                    items="${locations}" 
                                    var="currentLocation" 
                                    varStatus="loop">
                                    <option value="${currentLocation.locationId}">${currentLocation.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <!-- select form for SUPERPERSON -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Super Persons </label>
                        <div class="col-xs-5">
                                <select multiple name="superPersons" form="createOrganizationForm" path="superPersons">
                                <c:forEach
                                    items="${superPersons}" 
                                    var="currentSuperPerson" 
                                    varStatus="loop">
                                    <option value="${currentSuperPerson.superPersonId}">${currentSuperPerson.name}</option>
                                </c:forEach>
                            </select><span><sf:errors path="superPersons" cssclass="error"></sf:errors></span>
                        </div>
                    </div>






                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-5 col-xs-offset-3">
                    <button type="submit" class="btn btn-default" id="btnCreateOrganization">Add Organization</button>
                    <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/organization/organizations" formmethod="GET">Cancel</button>
                </div>
            </div>
        </sf:form>

    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>

<!-- 
NOTES


-->