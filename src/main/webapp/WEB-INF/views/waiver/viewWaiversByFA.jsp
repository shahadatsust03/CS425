<%-- 
    Document   : viewWaiversByFA
    Created on : Aug 11, 2014, 3:04:59 PM
    Author     : demodem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1>Pending Waivers:</h1> 
                <div class="panel-group" id="accordion">
                <c:forEach items="${waivers}" var="waiver"> 
                <div class="panel panel-default">
                                           
                        <div class="panel-heading" style="border:none;background:none;">
                            <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" h="classes/${classs.id} "href="#collapse${classs.id}">
                            ${waiver.id} </a>
                            </h4>
                        </div>
                            <c:if test="${loop.index ==0}">
                           <div id="collapse${waiver.id}" class="panel-collapse collapse in">
                         </c:if>
                         <c:if test="${loop.index >0}">
                             <div id="collapse${waiver.id}" class="panel-collapse collapse">
                         </c:if>
                        <div class="panel-body">
                         <div>Class : <a href="../waiverDetails/${waiver.id}"/>${waiver.yogaClass.className}</div>
                         <div>Reason :${waiver.reason}</div>
                         <div>Submitted Date :${waiver.submissionDate}</div>                         
                         <div>Customer Name : ${not empty waiver.customer ? waiver.customer.fullname:""}</div>
                         <div>Comments : ${waiver.comments}</div>
                         <div>Status : ${waiver.status}</div>
                         <div>Updated Date : ${waiver.updateDate}</div>                         
                         </div>                         
                         </c:forEach>  
                    </div>
                        <c:if test="${empty waivers}">
                        <b>No Pending requests. </b>
                    </c:if>
               <!-- <form action="../../waiver/waiverDetails/${waiver.id}" method="post">-->
                <!-- <table class="table table-striped" align="center">    
                    <td>Class</td>
                    <td>Reason</td>
                    <td>Submitted Date</td>
                    <td>Customer Name:</td>
                    <td>Comments</td>
                    <td>Status</td>
                    <td>Updated Date</td>
                    <td></td>
                    <c:forEach items="${waivers}" var="waiver">
                        <tr>      
                            <td><a href="../waiverDetails/${waiver.id}"/>${waiver.yogaClass.className}</td>
                            <td>${waiver.reason}</td>
                            <td>${waiver.submissionDate}</td>
                            <td>${waiver.customer.fullname}</td>
                            <td>${waiver.comments}</td>
                            <td>${waiver.status}</td>
                            <td>${waiver.updateDate}</td>                            
                        </tr> 
                    </c:forEach>
                </table> -->
               <!-- </form> -->
            </div></div>
    </body>
</html>
