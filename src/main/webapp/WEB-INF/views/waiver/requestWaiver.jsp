<%-- 
    Document   : requestWaiver
    Created on : Aug 8, 2014, 8:27:00 PM
    Author     : demodem
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Waiver</title>
        <%@include file="../header.jsp" %>
    </head>
    <body><%@include file="../nagivation.jsp" %>
        <div id="featureWrap"> 
            <c:if test="${empty User}">
                User Not Found.
            </c:if>
            <c:if test="${not empty User}">
            <form action="../waiver/submitWaiver/${User.id}" method="post">
        <h1>Request Waiver Form:</h1>
        <div><b>Select Class:</b>
        <div class="container">
                  <%@include file="../classes/classListPop.jsp" %>
            </div>
        </div>
        <div> <form input="text" name="reason"> Reason: </form></div>
        <div><br/><input type="submit" value="Submit Waiver"></div></form>
            </c:if>
        </div>
    </body>
</html>
