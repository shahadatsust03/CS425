<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../header.jsp" %>
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
        <div id="featureWrap">      
            <div class="container">
                <div class="row" style="margin:auto">
                    <div class="col-xs-3 col-md-2">
                        <ul class="nav nav-pills nav-stacked">
                            <li class="active"><a href="${pageContext.request.contextPath}/user/myaccount?render=ajax" id="userProfile">Profile</a></li>
                            <li><a href="${pageContext.request.contextPath}/unenrollments?render=ajax" id="userEnrollments">Enrollments</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/orders?render=ajax" id="userOrders">Orders</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-12 col-md-8" style="width:78%">
                        <c:if test="${not empty message}">
                            <b> ${message}</b>
                        </c:if>
                        <!--display flash message-->
                        <div id="serverResponseMsg"></div>
                        <div id="userAccountContainer">
                            <%@include file="myaccount_mini.jsp" %>
                        </div>
                        <!--credit card model -->
                        <div class="modal fade" id="creditcard-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="myModalLabel">New Credit card</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div id="credit-card-model-msg"> </div>
                                        <form id="newCreditCard" role="form" action="${pageContext.request.contextPath}/user/savecreditcard">
                                            <div class="form-group">
                                                <label for="cardnumber">Card number</label>
                                                <input type="text" class="form-control" id="cardnumber" name="cardnumber" placeholder="Enter card number">
                                            </div>
                                            <div class="form-group">
                                                <label for="expirydate">Expiry date</label>
                                                <input type="text" class="form-control" id="expirydate"  name="expirydate" placeholder="Enter expiry date"><div>Format: MM/YYYY</div>
                                            </div>                                                           
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" onclick="$(this).saveCreditCard('#newCreditCard')">Submit</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>                                                                                      
        </div>   
        <!-- /featureWrap -->
    </body>
</html>