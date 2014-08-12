<%-- 
    Document   : viewWaiversByFA
    Created on : Aug 11, 2014, 3:04:59 PM
    Author     : demodem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <h1>Pending Waivers:</h1>
               <!-- <form action="../../waiver/waiverDetails/${waiver.id}" method="post">-->
                <table class="table table-striped" align="center">    
                    <td>Class</td>
                    <td>Reason</td>
                    <td>Submitted Date</td>
                    <td>Faculty Advisor</td>
                    <td>Comments</td>
                    <td>Status</td>
                    <td>Updated Date</td>
                    <td></td>
                    <c:forEach items="${waivers}" var="waiver">
                        <tr>      
                            <td><a href="waiverDetails/${waiver.id}"/>${waiver.yogaClass.className}</td>
                            <td>${waiver.reason}</td>
                            <td>${waiver.submissionDate}</td>
                            <td>${waiver.faculty.fullname}</td>
                            <td>${waiver.comments}</td>
                            <td>${waiver.status}</td>
                            <td>${waiver.updateDate}</td>                            
                        </tr>
                    </c:forEach>
                </table>
               <!-- </form> -->
            </div></div>
    </body>
</html>
