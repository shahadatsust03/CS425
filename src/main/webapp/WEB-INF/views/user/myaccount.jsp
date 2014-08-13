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
                            <li><a href="#">Orders</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-12 col-md-8" style="width:78%">
                        <c:if test="${not empty message}">
                            <b> ${message}</b>
                        </c:if>
                            <div id="userAccountContainer">
                                 <%@include file="myaccount_ajax.jsp" %>
                            </div>
                    </div>
                </div>

            </div>                                                                                      
        </div>   
        <!-- /featureWrap -->
    </body>
</html>