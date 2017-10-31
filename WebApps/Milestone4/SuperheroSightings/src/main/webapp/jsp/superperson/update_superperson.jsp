<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit SuperPerson</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Edit SuperPerson</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-xs-5 col-xs-offset-3">
                    <div class="alert alert-danger alert-dismissable" id="validationError"
                         style="display:none;">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    </div>
                    <div class="alert alert-success alert-dismissable" id="successMsg"
                         style="display:none;">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <form id="updateSuperPersonForm" class="form-horizontal"
                      action="${pageContext.request.contextPath}/superperson/updateSuperPerson" method="POST">
                    <input type="hidden" value="${spvm.superPerson.superPersonId}" name="superPersonId">
                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Name </label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="name" value="${spvm.superPerson.name}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Powers </label>
                        <div class="col-xs-5">
                            <select multiple name="powers" form="updateSuperPersonForm">
                                <c:forEach
                                    items="${spvm.powers}" 
                                    var="currentPower" 
                                    varStatus="loop">
                                    <option selected="true" value="${currentPower.powerId}" >${currentPower.name}</option>
                                </c:forEach>
                                <c:forEach
                                    items="${doesntHavePowers}" 
                                    var="currentPower" 
                                    varStatus="loop">
                                    <option  value="${currentPower.powerId}" >${currentPower.name}</option>
                                </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Organizations </label>
                            <div class="col-xs-5">
                                <select multiple name="organizations" form="updateSuperPersonForm">
                                <c:forEach
                                    items="${spvm.organizations}" 
                                    var="currentOrganization" 
                                    varStatus="loop">
                                    <option selected="true" value="${currentOrganization.organizationId}">${currentOrganization.name}</option>
                                </c:forEach>
                                 <c:forEach
                                    items="${doesntHaveOrganizations}" 
                                    var="currentOrganization" 
                                    varStatus="loop">
                                    <option value="${currentOrganization.organizationId}">${currentOrganization.name}</option>
                                </c:forEach>
                                </select>
                            </div>
                        </div>            
                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Description </label>
                            <div class="col-xs-5">
                                <textarea rows ="10" type="textarea" class="form-control" name="description"
                                          >${spvm.superPerson.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Good or Evil? </label>
                            <div class="col-xs-5 inputGroupContainer">
                                <div class="input-group">
                                    <input <c:if test="${spvm.superPerson.isGood}">checked = "checked"</c:if>  type="radio" name="reputation" value="good" />  Good<br />
                                    <input <c:if test="${not spvm.superPerson.isGood}">checked = "checked"</c:if> type="radio" name="reputation" value="evil"/>  Evil<br />
                                    <input type="radio" name="reputation" value="unknown"/> Unknown<br />
                                </div>
                            </div>
                        </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-5 col-xs-offset-3">
                    <button type="submit" class="btn btn-default" id="btnCreateSuperPerson">Save Edit</button>
                    <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/superperson/superpersons" formmethod="GET">Cancel</button>
                </div>
            </div>
        </form>
            </div>
    <!-- Main Page Content Start -->
    <!-- Main Page Content Stop -->
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>