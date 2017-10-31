<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create SuperPerson</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Create SuperPerson</h1>
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
                <form id="createSuperPersonForm" class="form-horizontal"
                      action="${pageContext.request.contextPath}/superperson/createSuperPerson" method="POST">
                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Name </label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="name"
                                   placeholder="Enter Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Powers </label>
                        <div class="col-xs-5">
                            <select multiple name="powers" form="createSuperPersonForm">
                                <c:forEach
                                    items="${spvm.powers}" 
                                    var="currentPower" 
                                    varStatus="loop">
                                    <option value="${currentPower.powerId}">${currentPower.name}</option>
                                </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Organizations </label>
                            <div class="col-xs-5">
                                <select multiple name="organizations" form="createSuperPersonForm">
                                <c:forEach
                                    items="${spvm.organizations}" 
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
                                <input type="text" class="form-control" name="description"
                                       placeholder="Enter Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Good or Evil? </label>
                            <div class="col-xs-5 inputGroupContainer">
                                <div class="input-group">
                                    <input type="radio" name="reputation" value="good" />  Good<br />
                                    <input type="radio" name="reputation" value="evil"/>  Evil<br />
                                    <input type="radio" name="reputation" value="unknown"/> Unknown<br />
                                </div>
                            </div>
                        </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-5 col-xs-offset-3">
                    <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/superperson/superpersons" formmethod="GET">Cancel</button>
                    <button type="submit" class="btn btn-default" id="btnCreateSuperPerson">Create Super Person</button>
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