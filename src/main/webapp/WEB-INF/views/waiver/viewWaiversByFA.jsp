<%-- 
    Document   : viewWaiversByFA
    Created on : Aug 11, 2014, 3:04:59 PM
    Author     : demodem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />
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
                <c:if test="${not empty message}">
                    <b> ${message}</b>
                </c:if>
                <h3>Waivers:</h3>
               <!-- <form action="../../waiver/waiverDetails/${waiver.id}" method="post">-->
                <div class="row">
                    <div class="col-sm-8 text-center feature">
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
                                    <td><a href="../waiverDetails/${waiver.id}"/>${waiver.yogaClass.className}</td>
                                    <td>${waiver.reason}</td>
                                    <td>${waiver.submissionDate}</td>
                                    <td>${waiver.faculty.fullname}</td>
                                    <td>${waiver.comments}</td>
                                    <td>${waiver.status}</td>
                                    <td>${waiver.updateDate}</td>                            
                                </tr>
                            </c:forEach>
                            <c:if test="${empty waivers}">
                                <b>No Pending requests. </b>
                            </c:if>
                        </table>
                        <!-- </form> -->
                    </div>
                    <div class="col-sm-4 text-center feature">
                        <h4></h4>
                        <p>

                            <%@include file="../user/userprofile.jsp" %>
                            <%@include file="../faculty/navi.jsp" %>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
