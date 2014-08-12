<%-- 
    Document   : product_list
    Created on : Jul 30, 2014, 2:14:09 PM
    Author     : Shahadat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

               <div class="panel-group" id="accordion">
                <c:forEach items="${semesters}" var="semester" varStatus="loop">
                 <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" h="semesters/${semester.id} "href="#collapse${semester.id}">
                         ${semester.semesterName}
                         
                        </a>
                      </h4>
                    </div>
                         <c:if test="${loop.index ==0}">
                           <div id="collapse${semester.id}" class="panel-collapse collapse in">
                         </c:if>
                         <c:if test="${loop.index >0}">
                             <div id="collapse${semester.id}" class="panel-collapse collapse">
                         </c:if>
                      <div class="panel-body">
                        <div>Start Date: ${semester.startDate}</div>
                       <div>End Date: ${semester.endDate}</div>  
                       <div><c:if test="${not empty classs.sections}">Sections:</c:if> 
                         <c:if test="${not empty semester.sections}">
                             <table class="table">
                                 <thead>
                                     <tr>
                                         <th></th>
                                         <th>Section </th>
                                         <th>Location </th>
                                          <th>Limit </th>
                                         <th>Description </th>
                                     </tr>
                                 </thead>
                               <c:forEach items="${semester.sections}" var="section"> 
                                 <tr>
                                     <td>
                                         <a href="${pageContext.request.contextPath}/processenrollment/${section.id}"> Enroll    </a>
                                     </td>
                                     <td>
                                            <div>${section.sectionName}</div>
                                     </td>
                                     <td>
                                            <div>${section.location}</div>
                                     </td>
                                     <td>
                                            <div>Limit: ${section.classLimit} &nbsp;&nbsp;</div>
                                     </td>
                                     <td>
                                           <div>Total Enrollments: ${section.getTotalEnrollment()} &nbsp;&nbsp;</div>
                                     </td>
                                            <c:if test="${not empty section.faculty}">Faculty:
                                               <div>${section.faculty.fullname} &nbsp;&nbsp;</div></c:if>
                                            </c:forEach>
                                        </td>
                                     </tr>
                             </table>
                         </c:if>
                         </div>
                    </div>
                  </div>
               </c:forEach>
              <div>
</table>
