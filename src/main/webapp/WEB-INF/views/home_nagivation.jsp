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
                    <a class="navbar-brand" href="#topWrap">						
                            YOGA<span class="title">Studio</span>
                    </a>
            </div>
            <div class="collapse navbar-collapse appiNav">
                    <ul class="nav navbar-nav">
                        <c:if test="${pageContext.request.userPrincipal.name == null}">                            
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li> 
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/classes">Classes</a></li>
                            <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                <li><a href="${pageContext.request.contextPath}/sections">Sections</a></li>
                                
                                <li><a href="${pageContext.request.contextPath}/semesters">Semesters</a></li>  
                            </sec:authorize>
              
                             <c:if test="${pageContext.request.userPrincipal.name != null}">                            
                            <sec:authorize ifAnyGranted="ROLE_USER">
                                 <li><a href="${pageContext.request.contextPath}/enrollments">Enrollment</a></li>
                                <li><a href="${pageContext.request.contextPath}/unenrollments">Unenroll</a></li>
                            </sec:authorize>
                            </c:if>
                                <li><a href="${pageContext.request.contextPath}/products">Products</a></li>  
                             <c:if test="${pageContext.request.userPrincipal.name != null}">
                             <sec:authorize ifAnyGranted="ROLE_ADMIN">
                             <li>
                                 <a href="${pageContext.request.contextPath}/faculty">Faculty</a>                               
                             </li>  
                             <li>
                                 <a href="${pageContext.request.contextPath}/customers/customer">Customers</a>                               
                             </li>
                             </sec:authorize>
                             
                            
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name == null}">
                              <li><a href="#" id="registerCustomer" onclick="function register(){return false;}" data-toggle="modal" data-target="#regisration-modal">
                                      Sign up</a>
                              </li>
                            
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <li><a href="<c:url value="${pageContext.request.contextPath}/j_spring_security_logout"/>">Logout</a></li>
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <li><a href="${pageContext.request.contextPath}/user/myaccount">My account</a></li>
                            </c:if>
                              <sec:authorize access="!hasRole('ROLE_USER')">
                                <li><a href="${pageContext.request.contextPath}/user/orders">Orders</a></li>
                              </sec:authorize>
                                <li>
                                  <a href="${pageContext.request.contextPath}/products/cart" >
                                    <span class="badge pull-right" id="myCart">0</span>
                                    <span class="glyphicon glyphicon-shopping-cart"></span>
                                 </a>
                              </li>  
                        
                               <!--Add a Div with the class "simpleCart_items" to show your shopping cart area.-->
                          
                    </ul>
            </div><!--/.nav-collapse -->
    </div>
</div>

<div id="topWrap" class="jumbotron" style="padding-top:0px">
    <div class="container">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                  <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                  <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                  <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                  <div class="item active">
                    <img src="${pageContext.request.contextPath}/resources/public/images/slide/slide9.jpg" alt="...">
                    <div class="carousel-caption">
                      slide 1
                    </div>
                  </div>
                  <div class="item">
                    <img src="${pageContext.request.contextPath}/resources/public/images/slide/slide6.jpg" alt="...">
                    <div class="carousel-caption">
                        slide2
                    </div>
                  </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                  <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                  <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
              </div>
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
