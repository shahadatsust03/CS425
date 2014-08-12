<%-- 
    Document   : product_list
    Created on : Jul 30, 2014, 2:14:09 PM
    Author     : Shahadat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

               <div class="panel-group" id="accordion">
                <c:forEach items="${classes}" var="classs" varStatus="loop">
                 <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" h="classes/${classs.id} "href="#collapse${classs.id}">
                         ${classs.className}
                         
                        </a>
                      </h4>
                    </div>
                         <c:if test="${loop.index ==0}">
                           <div id="collapse${classs.id}" class="panel-collapse collapse in">
                         </c:if>
                         <c:if test="${loop.index >0}">
                             <div id="collapse${classs.id}" class="panel-collapse collapse">
                         </c:if>
                      <div class="panel-body">
                        <div>Description: ${classs.description}</div>
                       <div>Tution Fee: ${classs.fee}</div>
                       <div><c:if test="${not empty classs.prerequisteClasses}">Prerequesties:</c:if> 
                         <c:if test="${not empty classs.prerequisteClasses}">
                             <c:forEach items="${classs.prerequisteClasses}" var="preReq">                                 
                                 ${preReq.className} &nbsp;&nbsp;
                             </c:forEach>
                         </c:if>
                         </div>
                         <div><c:if test="${not empty classs.sections}">Sections:</c:if> 
                         <c:if test="${not empty classs.sections}">
                             <c:forEach items="${classs.sections}" var="section">                                 
                             <div>Section name: ${section.sectionName} &nbsp;&nbsp;</div>
                             <div>Location: ${section.location} &nbsp;&nbsp;</div>
                             <div>Limit: ${section.classLimit} &nbsp;&nbsp;</div>
                             
                                <div>Total Enrollments: ${section.getTotalEnrollment()} &nbsp;&nbsp;</div>
                             
                             <c:if test="${not empty section.faculty}">Faculty:
                                <div>${section.faculty.fullname} &nbsp;&nbsp;</div></c:if>
                             </c:forEach>
                         </c:if>
                         </div>
                         <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                             <div style="margin-top:10px;">
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/editclassform/${classs.id}">Edit Class</a>
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/removeclass/${classs.id}">Remove Class</a>
                            </div>
                            <div style="margin-top:10px;">
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/section/add/${classs.id}">Add Section</a>
                            </div>
                       </sec:authorize>

                      </div>
                    </div>
                  </div>
               </c:forEach>
              <div>
</table>
