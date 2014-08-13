
<%-- 
    Document   : main_Section_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <div class="panel-group" id="accordion">
                            
                <c:forEach items="${sections}" var="section" varStatus="loop">
                 <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                         <a data-toggle="collapse" data-parent="#accordion" h="section/${section.id} "href="#collapse${section.id}">
                         ${section.sectionName}
                         
                        </a>
                      </h4>
                    </div>    
                    <c:if test="${loop.index ==0}">
                      <div id="collapse${section.id}" class="panel-collapse collapse in">
                    </c:if>
                    <c:if test="${loop.index >0}">
                        <div id="collapse${section.id}" class="panel-collapse collapse">
                    </c:if>
                      <div class="panel-body">
                        <div>Description ${section.description}</div>
                       <div>Class: ${section.classEntity.className}</div>
                       <div>Location: ${section.location}</div>
                       <div>Limit: ${section.classLimit}</div>      
                        <div>Total Enrollments: ${section.getTotalEnrollment()}</div>
                         <c:if test="${not empty section.faculty ||  section.faculty!= null}">Faculty:
                               <div>${section.faculty.fullname} &nbsp;&nbsp;</div>
                         </c:if>
                         
                         <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                             <div style="margin-top:10px;">
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/section/editSection/${section.id}">Edit Section</a>
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/section/removeSection/${section.id}">Remove Section</a>
                             </div>
                       </sec:authorize>                      
                      </div>                    
                  </div>
               </div>
        </c:forEach>
</div>
</div>
