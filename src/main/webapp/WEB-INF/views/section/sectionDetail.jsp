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
                    <div class="panel panel-default">
                        <div class="panel-heading" style="border:none;background:none;">
                            <h4>${section.sectionName}
                            </h4>
                        </div>
                        <%java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss"); %> 
                            
                        <div class="panel-body">
                        <div>Description: ${section.descripton}</div>
                        <div>Class: ${section.classEntity.className}</div>
                        <div>Location: ${section.location}</div>
                        <div>Limit: ${section.classLimit}</div>                     
                        <c:forEach items="${section.scheduleList}" var="schedule">                     
                        <div>Day of week: ${schedule.dayOfWeek}</div>
                        <div>Start date: ${schedule.getStartTime()}</div>
                        <div>End date: ${schedule.getEndTime()}</div>
                        </c:forEach>
                        </div>
                    </div>
                        <div>
                        <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                        <h5>
                            <td><a href="${pageContext.request.contextPath}/section/edit_section/${section.id}">Edit section</a></td>
                            <td><a href="${pageContext.request.contextPath}/section/remove_section/${section.id}">Remove section</a></td>
                        </h5>
                        </sec:authorize>
                        </div>
                 </div> 
                        
            </div>
    </body>
</html>
