<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine Spring MVC</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/vendingStyles.css" rel="stylesheet">
    </head>

    <body>
        <h1 style="text-align: center">Vending Machine</h1>
        <hr/>
        <div class="container-fluid" style="border:1px solid black">
            <!-- slots -->
            <div class="col-xs-8">
                <div class="row">

                    <c:forEach items="${itemList}" var="currentItem">
<!--                        c:if test="{not loop.first and loop.index % 3 == 0}">
                        </div>
                        <div class="row">
                        /c:if>  -->
                        <a href="setNumber?userChoice=${currentItem.itemSlot}">
                        <div class="col-xs-3" id="machineSlot">
<!--                            <input type="hidden" name="itemSlot" value="{currentItem.itemSlot}">-->
                            <p>
                                <c:out value="${currentItem.itemSlot}" /> <br> <br>
                                <c:out value="${currentItem.itemName}" /> <br> <br>
                                <c:out value="$${currentItem.itemPrice}" /> <br> <br> 
                                Quantity: <c:out value="${currentItem.itemQuantity}" />
                            </p>
                        </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
            <!-- forms -->
            <div class="col-xs-4">
                <div id="addMoney" class="row">
                    <h4>Total $ In:</h4>

                    <div class="col-xs-12">
                        <div class="col-xs-3"></div>
                        <!-- blank space of 3 around moneyInserted, which takes up 6 ... try col-xs-offset ??? -->       
                        <div class="col-xs-6">
                            <input type="text" class="form-control" id="moneyInserted" value="${totalMoney}" readonly="true" />
                        </div>
                        <div class="col-xs-3"></div>
                    </div>

                    <form role="form" method="POST" action="addMoney">        
                        <div class="col-xs-12">
                            <div class="col-xs-1"></div>
                            <div class="col-xs-5" style="text-align: center">
                                <button id="dollar-button" value="1.00" name="money">
                                    Add Dollar
                                </button>
                            </div>

                            <div class="col-xs-5" style="text-align: center">
                                <button id="quarter-button" value="0.25" name="money">
                                    Add Quarter
                                </button>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>

                        <div class="col-xs-12">
                            <div class="col-xs-1"></div>
                            <div class="col-xs-5" style="text-align: center">
                                <button id="dime-button" value="0.10" name="money">
                                    Add Dime
                                </button>  
                            </div>

                            <div class="col-xs-5" style="text-align: center">  
                                <button id="nickel-button" value="0.05" name="money">
                                    Add Nickel
                                </button>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>   
                    </form>
                </div>

                <hr/>

                <div id="makePurchase" class="row">
                    <h4>Messages:</h4>

                    <form role="form" method="POST" action="buyItem" name="buyForm">
                        <div class="row">
                            <input type="text" class="form-control" value="${param.messageText}" id="messagesField" placeholder="" readonly="true" />
                        </div>

                        <div class="row" style="text-align: right">
                            <label for="item-selection" class="col-xs-4 control-label">
                                Item:
                            </label>

                            <div class="col-xs-8">
                                <input type="text" class="form-control" id="item-selection" name="itemNumber" value="${userChoice}" placeholder="nothing selected" readonly="true"/>
                            </div>
                        </div>

                        <div class="row" style="text-align: center">
                            <button id="make-purchase-button">
                                Make Purchase
                            </button>
                        </div>
                    </form>
                </div>

                <hr/>

                <div id="makeChange" class="row text-center">
                    <h4>Change:</h4>

                    <div class="row">
                        <a onclick="takeChange()">
                            <input type="text" class="form-control" id="changeField" value="${param.quarters} ${param.dimes} ${param.nickels} ${param.pennies}" placeholder="" readonly="true" />
                        </a>
                    </div>
                    <form action="changeReturn" id="changeForm" method="GET">
                    <button href="changeReturn" id="change-return-button">
                        Change Return
                    </button>
                    </form>
                </div>

            </div>

        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>

</html>