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
                    <style>
    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=80);
    }
    .white_content {
        display: none;
        position: absolute;
        top: 25%;
        left: 25%;
        width: 50%;
        height: 50%;
        padding: 16px;
        border: 16px solid orange;
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
</style>


        
        <%@include file="../header.jsp" %>
        <%@include file="sectionList.jsp" %>
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
                            
                        <c:if test="${not empty section.classEntity}"><div>Class: ${section.classEntity.className}</div>
                            </c:if>
                        <c:if test="${not empty section.semester}"><div>Semester: ${section.semester.semesterName}</div>
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
                             <td><a href = "javascript:void(0)" onclick = "document.getElementById('sectionList').style.display='block';document.getElementById('fade').style.display='block'">Assign Faculty</a></p>
                             </td>
                            
                                 </div>
                        </h5>
                        </sec:authorize>
                        </div>
                 </div> 
                        
            </div>
                     
    </body>
</html>
