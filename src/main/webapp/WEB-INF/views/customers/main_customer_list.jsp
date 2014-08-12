<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <div class="panel-group" id="accordion">
                              
                <c:forEach items="${customers}" var="customer" varStatus="loop">
                 <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                         <a data-toggle="collapse" data-parent="#accordion" h="customer/${customer.id} "href="#collapse${customer.id}">
                         ${customer.fullname}                         
                        </a>
                      </h4>
                    </div>    
                    <c:if test="${loop.index ==0}">
                      <div id="collapse${customer.id}" class="panel-collapse collapse in">
                    </c:if>
                    <c:if test="${loop.index >0}">
                        <div id="collapse${customer.id}" class="panel-collapse collapse">
                    </c:if>
                                          <div class="panel-body">
                        <div>Street: ${customer.street}</div>
                        <div>City: ${customer.city}</div>
                        <div>State: ${customer.state}</div>
                                               
                       <div>Email: ${customer.email}</div>
                       <div><c:if test="${not empty customer.enrollments}">Enrollments:</c:if> 
                         <c:if test="${not empty customer.enrollments}">
                             <c:forEach items="${customer.enrollments}" var="enroll">                                 
                                 <div>Class name: ${enroll.classEn.className} </div>
                                 <div>Section name: ${enroll.section.sectionName} </div>                                 
                             </c:forEach>
                         </c:if>
                         </div>
                         
                         <div><c:if test="${not empty customer.orders}">Orders:</c:if> 
                         <c:if test="${not empty customer.orders}">
                             <c:forEach items="${customer.orders}" var="order">                                 
                                 <div>Date: ${order.dateOfOrder} </div>
                                 <div>Total amount: ${order.totalAmount} </div>                                 
                             </c:forEach>
                         </c:if>
                         <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                             <div style="margin-top:10px;">
                              <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/customer/removeCustomer/${customer.id}">Remove Customer</a>
                            </div>                            
                       </sec:authorize>

                      </div>
                    </div>
                  </div>
               </c:forEach>
              <div>
    </div>
</div>
</div>
