<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html> 
<html>
    <head>
        <title>Update Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Update Organization</h1>
                    <p>NOTE: If this organization has a new location, you must <a href="${pageContext.request.contextPath}/location/displayCreateLocationPage">register the location first</a></p>
                </div>
            </div>
            <hr/>
            <div class="row"> <!-- form wide errors? -->

            </div>
            <div class="row">
                <sf:form id="updateOrganizationForm" class="form-horizontal" commandName="ocm"
                         action="${pageContext.request.contextPath}/organization/updateOrganization" method="POST">
                    <input type="hidden" value="${ovm.organization.organizationId}" name="organizationId" path="organizationId"/>


                    <!-- text form for NAME -->
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Name </label>
                        <div class="col-xs-5">

                            <input id="name" name="name" type="text" path="name" value="${ovm.organization.name}"/><span><sf:errors path="name" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- text form for DESCRIPTION -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Description</label>
                            <div class="col-xs-5">
                            <sf:errors path="description" cssclass="error"></sf:errors>
                                <textarea name="description" id="description" class="form-control" rows="5"
                                           path="description">${ovm.organization.description}</textarea>
                        </div>
                    </div>

                    <!-- tel form for PHONE -->
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Telephone </label>
                        <div class="col-xs-5">

                            <input id="phone" name="phone" type="tel" path="phone" value="${ovm.organization.phone}"/>
                            <p><sf:errors path="phone" cssclass="error"></sf:errors></p>
                            </div>
                        </div>

                        <!-- radio form for ISGOOD -->
                        
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Reputation </label>
                            <div class="col-xs-5">
                            <sf:errors path="isGood" cssclass="error"></sf:errors>
                                <div class="input-group">
                                <c:choose>
                                    <c:when test="${ovm.organization.isGood}">
                                        <input name="isGood" type="radio" path="isGood" value="good" checked/> Good <br>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="isGood" type="radio" path="isGood" value="good" /> Good <br>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${not ovm.organization.isGood}">
                                        <input name="isGood" type="radio" path="isGood" value="evil" checked/> Evil <br>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="isGood" type="radio" path="isGood" value="evil" /> Evil <br>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>
                    </div>


                    <!-- select form for LOCATION -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Location</label>
                        <div class="col-xs-5">
                            <select name="locationId" id="locationId" form="updateOrganizationForm" path="locationId">
                                <c:forEach
                                    items="${allLocations}" 
                                    var="currentLocation">
                                    <option value="${currentLocation.locationId}"
                                            <c:if test="${currentLocation.locationId == ovm.location.locationId}">
                                                selected
                                            </c:if>
                                            >
                                        ${currentLocation.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <!-- select form for SUPERPERSON -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Super Persons </label>
                        <div class="col-xs-5">
                            <select multiple name="superPersons" form="updateOrganizationForm" path="superPersons">
                                <c:forEach
                                    items="${allSuperPersons}" 
                                    var="currentSuperPerson">
                                    <option value="${currentSuperPerson.superPersonId}"
                                            <c:forEach
                                                items="${ovm.superPersons}"
                                                var="currentKnownSuperPerson"
                                                >
                                                <c:if test="${currentSuperPerson.superPersonId == currentKnownSuperPerson.superPersonId}">
                                                    selected
                                                </c:if>
                                            </c:forEach>
                                            >
                                        ${currentSuperPerson.name}
                                    </option>
                                </c:forEach>
                            </select><span><sf:errors path="superPersons" cssclass="error"></sf:errors></span>
                            </div>
                        </div>






                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-5 col-xs-offset-3">
                        <button type="submit" class="btn btn-default" id="btnCreateOrganization">Update Organization</button>
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