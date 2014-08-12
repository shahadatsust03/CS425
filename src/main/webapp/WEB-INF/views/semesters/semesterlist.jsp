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
                       <c:if test="${not empty semester.sections}">
                             <c:forEach items="${semester.sections}" var="section">                                 
                                 ${section.sectionName} &nbsp;&nbsp;
                             </c:forEach>
                         </c:if>
                         
                         <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                             <div style="margin-top:10px;">
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/editsemesterform/${semester.id}">Edit Semester</a>
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/removesemester/${semester.id}">Remove Semester</a>
                            </div>                            
                       </sec:authorize>

                      </div>
                    </div>
                  </div>
               </c:forEach>
              <div>
</table>
