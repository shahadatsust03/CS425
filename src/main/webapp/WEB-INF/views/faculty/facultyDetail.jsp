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
                            <h4>Faculty name: ${faculty.fullname}
                            </h4>
                        </div>
                        <div class="panel-body">
                        <div>Years of Experience: ${faculty.getYearsOfExperience()}</div>
                        <div>Specialization: ${faculty.getSpecialization()}</div>
                        <div>Contact Number: ${faculty.contactNum}</div>                        
                         <br>
                        <c:if test="${not empty faculty.sections}"> <div>Section List: </div> </c:if>
                        <c:forEach items="${faculty.sections}" var="section">     
                        
                        <div>Section name: ${section.sectionName}</div>
                            
                        <c:if test="${not empty section.classEntity}"><div>Day of week: ${section.classEntity.className}</div>
                            </c:if>
                        <c:if test="${not empty section.semester}"><div>Day of week: ${section.semester.semestertName}</div>
                            </c:if>
                            <c:if test="${not empty section.scheduleList}"> <div>Schedule List: </div> </c:if>
                            <c:forEach items="${section.scheduleList}" var="schedule"> 
                            <div>Day of week: ${schedule.dayOfWeek}</div>
                            <div>Start date: ${schedule.getStartTime()}</div>
                            <div>End date: ${schedule.getEndTime()}</div>
                            </c:forEach>
                        </c:forEach>
                        </div>
                    </div>
                        <div>
                        <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                        <h5>
                            <div>
                                
                            <td><a href="${pageContext.request.contextPath}/faculty/editFaculty/${faculty.id}">Edit Faculty</a></td>
                            <td><a href="${pageContext.request.contextPath}/faculty/removeFaculty/${faculty.id}">Remove Faculty</a></td>
                     
                            
                                 </div>
                        </h5>
                        </sec:authorize>
                        </div>
                 </div> 
                        
            </div>
    </body>
</html>
