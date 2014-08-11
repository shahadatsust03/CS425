<%-- 
    Document   : viewWaivers
    Created on : Aug 10, 2014, 3:28:04 PM
    Author     : demodem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requested Waivers</title>
        <%@include file="../header.jsp" %>
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
        <div id="featureWrap">   
           
            <div class="container">
        <h1>Requested Waivers:</h1>
        <table class="table table-striped" align="center">    
            <td>Class</td>
            <td>Reason</td>
            <td>Submitted Date</td>
            <td>Faculty Advisor</td>
            <td>Comments</td>
            <td>Status</td>
            <td>Updated Date</td>
            <c:forEach items="${waivers}" var="waiver">
                <tr>      
                    <td>${waiver.yogaClass.className}</td>
                    <td>${waiver.reason}</td>
                    <td>${waiver.submissionDate}</td>
                    <td>${waiver.faculty.fullname}</td>
                    <td>${waiver.comments}</td>
                    <td>${waiver.status}</td>
                    <td>${waiver.updateDate}</td>
                </tr>
            </c:forEach>
        </table>
        </div>
            </div>
    </body>
</html>
