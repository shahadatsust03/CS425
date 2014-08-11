<%-- 
    Document   : addProduct
    Created on : Aug 6, 2014, 4:36:16 PM
    Author     : Shahadat
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <%@include file="semester_navigation2.jsp" %>
                    <div class="panel panel-default">
                        <div class="panel-heading" style="border:none;background:none;">
                            <h4>${semester.semesterName}
                            </h4>
                        </div>
                        <div class="panel-body">
                         <table>
                             <tr><td>Start Date : </td><td>${semester.startDate}</td>
                             <tr><td>End Date: </td><td>${semester.endDate}</div></td> 
                         </table>
                        </div>                                               
                    </div>
                </div>
    </div> 
    </body>
</html>
