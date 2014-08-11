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
            <c:if test="${empty user}">
                User Not Found.
            </c:if>
            <c:if test="${not empty user}">
                <form action="../../waiver/submitWaiver/${user.id}" method="post">
                    <h1>Request Waiver Form:</h1>

                    <div class="container">                                        
                        <div>
                            <!--         <form role="form"> -->

                            <table class="table table-striped">
                                <div><b>Select Class:</b>
                                    <p>Class List</p>
                                    <td>Select</td>
                                    <td>Name</td>
                                    <td>Description</td>
                                    <td>Tution Fee</td>
                                    <c:forEach items="${classes}" var="classes">
                                        <tr>      
                                        <div>
                                            <td><input type="radio" name="class_id" value="${classes.id}">

                                            <td>${classes.className}</td>
                                            <td>${classes.description}</td>
                                            <td>${classes.fee}</td>          

                                        </div>

                                        </tr>
                                    </c:forEach>

                                </div>
                            </table>
                            <div class="form-group">
                                <label for="reason">Reason:</label>
                                <input type="text" class="form-control" id="reason" placeholder="Reason"  name="reason"/>
                            </div>
                        </div>
                    </div>
                    <div><br/><input type="submit" value="Submit Waiver"></div></form>
                    </c:if>
        </div>
    </body>
</html>
