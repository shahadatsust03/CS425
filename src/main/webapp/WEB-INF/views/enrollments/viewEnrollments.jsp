<%-- 
    Document   : viewEnrollments
    Created on : Aug 12, 2014, 12:08:26 PM
    Author     : demodem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Enrollments</title>
        <%@include file="../header.jsp" %>
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
        <div id="featureWrap">   
           
            <div class="container">
                <c:if test="${not empty message}">
                        <b> ${message}</b>
                    </c:if>
        <h1>Enrollments:</h1>
        <table class="table table-striped" align="center">
        <td>Id</td>
            <td>Class</td>
            <td>Section </td>
            <td>Enrolled Date</td>            
            <td>Status</td>            
            <c:forEach items="${enrollments}" var="enrollment">
                <tr>      
                    <td>${enrollment.id}</td>
                    <td>${not empty enrollment.classEn ? enrollment.classEn.className : ""}</td>
                    <td>${not empty enrollment.section ? enrollment.section.sectionName : ""}</td>
                    <td>${enrollment.enrolledDate}</td>                                        
                    <td>${waiver.status}</td>                    
                </tr>
            </c:forEach>
        </table>
        </div>
            </div>
    </body>
</html>
