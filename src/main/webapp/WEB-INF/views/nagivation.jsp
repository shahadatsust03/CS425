<%-- 
    Document   : nagivation
    Created on : Jul 30, 2014, 12:05:15 PM
    Author     : eTunkara
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
            <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">						
                            YOGA<span class="title">Studio</span>
                    </a>
            </div>
            <div class="collapse navbar-collapse appiNav">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li> 
                        <li><a href="${pageContext.request.contextPath}/classes">Classes</a></li>
                            <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                <li><a href="${pageContext.request.contextPath}/sections">Sections</a></li>
                                <li><a href="${pageContext.request.contextPath}/semesters">Semesters</a></li>                                
                            </sec:authorize>
              
                             <c:if test="${pageContext.request.userPrincipal.name != null}">                            
                            <sec:authorize ifAnyGranted="ROLE_USER">
                                 <!--<li><a href="/enrollments">Enrollment</a></li> -->
                                <!--<li><a href="${pageContext.request.contextPath}/unenrollments">Unenroll</a></li> -->
                                 <!--<li><a href="${pageContext.request.contextPath}/waiver/viewWaivers">View Waivers</a></li> -->
                               <!-- <li><a href="${pageContext.request.contextPath}/enrollments">View Enrollment</a></li> -->
                            </sec:authorize>
                            </c:if>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">                            
                       <!--  <sec:authorize ifAnyGranted="ROLE_FACULTY">
                             <li><a href="${pageContext.request.contextPath}/waiver/viewWaiversByFA">View Waivers</a></li>    
                                </sec:authorize> -->
                        </c:if>
                                
                                 <c:if test="${pageContext.request.userPrincipal.name != null}">                            
                        <sec:authorize ifAnyGranted="ROLE_USER">
                             <li><a href="${pageContext.request.contextPath}/waiver/viewWaivers">View Waivers</a></li>    
                             <li><a href="${pageContext.request.contextPath}/viewEnrollments">View Enrollment</a></li> 
                                </sec:authorize> 
                             </c:if>
                                <li><a href="${pageContext.request.contextPath}/products">Products</a></li>  
                              <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                 <li>
                                  <a href="${pageContext.request.contextPath}/faculty">Faculty</a>                               
                                </li>  
                             </sec:authorize>
                             <c:if test="${pageContext.request.userPrincipal.name == null}">
                              <li><a href="#" id="registerCustomer" onclick="function register(){return false;}" data-toggle="modal" data-target="#regisration-modal">
                                      Sign up</a>
                              </li>
                            
                            </c:if>
                            
                                <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <li><a href="${pageContext.request.contextPath}/user/myaccount">My account</a></li>
                            </c:if>
                                <c:url value="${pageContext.request.contextPath}/j_spring_security_logout" var="logoutUrl" />
                              <li><a href="${logoutUrl}">Logout</a></li>
                            </c:if>
                                <sec:authorize access="!hasRole('ROLE_ADMIN')">      
                                <li>
                                  <a href="${pageContext.request.contextPath}/products/cart" >
                                    <span class="badge pull-right" id="myCart">0</span>
                                    <span class="glyphicon glyphicon-shopping-cart"></span>
                                 </a>
                              </li>  
                              </sec:authorize>
                               <!--Add a Div with the class "simpleCart_items" to show your shopping cart area.-->
                          
                    </ul>
            </div><!--/.nav-collapse -->
    </div>
</div>

<div id="topWrap" class="jumbotron">
    
    <div style="text-align: center">
            <h4 style="color:#fff;margin:0;padding:0">${pageTitle}</h4>
      </div>
</div>
<!-- registration modal -->
                                <div class="modal fade" id="regisration-modal" tabindex="-1" role="dialog" aria-labelledby="registrationLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                      <div class="modal-content">
                                        <div class="modal-header">
                                          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                          <h4 class="modal-title" id="registrationLabel">Sign up</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div id="registration-model-msg"></div>
                                              <%@include file="user/register_form.jsp" %>    
                                        </div>
                                        <div class="modal-footer">
                                          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                          <button type="button" class="btn btn-primary" onclick="$(this).registerUser('#register-customer-form')">Submit</button>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
