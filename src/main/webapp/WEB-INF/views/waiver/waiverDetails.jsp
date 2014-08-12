<%-- 
    Document   : waiverDetails
    Created on : Aug 11, 2014, 4:23:25 PM
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
            <c:if test="${empty waiver}">
                User Not Found.
            </c:if>
            <c:if test="${not empty waiver}">
                <div class="container">
                    <h1>Waiver Details:</h1>
                    <form action="../../waiver/respondOnWaiver/${waiver.id}" method="post">
                        <table class="table table-striped" align="center">    
                            <td>Class</td>
                            <td>Reason</td>
                            <td>Submitted Date</td>
                            <td>Faculty Advisor</td>
                            <td>Comments</td>
                            <td>Status</td>
                            <td>Updated Date</td>                                       

                            <tr>      
                                <td>${waiver.yogaClass.className}</td>
                                <td>${waiver.reason}</td>
                                <td>${waiver.submissionDate}</td>
                                <td>${waiver.faculty.fullname}</td>
                                <td>${waiver.comments}</td>
                                <td>${waiver.status}</td>
                                <td>${waiver.updateDate}</td>                                                                                                                                                         
                            </tr>

                        </table>
                        <h1>Enter Comments:</h1>
                        <div class="form-group">
                            <label for="reason">Comments:</label>
                            <input type="text" class="form-control" id="comments" placeholder="Comments"  name="comments"/>
                        </div>
                        <div align="center"><br/><input name="response" type="submit" value="Approve Waiver"><br><<input name="response" type="submit" value="Reject Waiver">></div>               
                    </form>
                </c:if>
            </div></div>
    </body>
</html>
