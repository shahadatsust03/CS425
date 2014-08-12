<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <div class="panel-group" id="accordion">
                              
                <c:forEach items="${facultys}" var="faculty" varStatus="loop">
                 <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                         <a data-toggle="collapse" data-parent="#accordion" h="faculty/${faculty.id} "href="#collapse${faculty.id}">
                         ${faculty.fullname}
                         
                        </a>
                      </h4>
                    </div>    
                    <c:if test="${loop.index ==0}">
                      <div id="collapse${faculty.id}" class="panel-collapse collapse in">
                    </c:if>
                    <c:if test="${loop.index >0}">
                        <div id="collapse${faculty.id}" class="panel-collapse collapse">
                    </c:if>
                                          <div class="panel-body">
                        <div>Specialization: ${faculty.getSpecialization()}</div>
                       <div>Years of experience: ${faculty.getYearsOfExperience()}</div>
                       <div>Email: ${faculty.email}</div>
                       <div><c:if test="${not empty faculty.sections}">Sections:</c:if> 
                         <c:if test="${not empty faculty.sections}">
                             <c:forEach items="${faculty.sections}" var="sec">                                 
                                 <div>${sec.sectionName} </div>
                                 <div>${sec.location} </div>
                             </c:forEach>
                         </c:if>
                         </div>
                         <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                             <div style="margin-top:10px;">
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/faculty/editFaculty/${faculty.id}">Edit Faculty</a>
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/faculty/removeFaculty/${faculty.id}">Remove Faculty</a>
                            </div>
                            <div style="margin-top:10px;">
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href = "javascript:void(0)" onclick = "document.getElementById('sectionList').style.display='block';document.getElementById('fade').style.display='block'">Assign Faculty</a>               
                            </div>
                       </sec:authorize>

                      </div>
                    </div>
                  </div>
               </c:forEach>
              <div>
    </div>
</div>
