<%-- 
    Document   : nagivation
    Created on : Jul 30, 2014, 12:05:15 PM
    Author     : eTunkara
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li> 
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
                             
                             <li>
                                 <a href="${pageContext.request.contextPath}/faculty">Faculty</a>                               
                             </li>  
                            
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name == null}">
                              <li><a href="#testimonialsWrap">Login</a></li>
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <li><a href="<c:url value="j_spring_security_logout"/>">Logout</a></li>
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <li><a href="${pageContext.request.contextPath}/user/myaccount">My account</a></li>
                            </c:if>
                              <sec:authorize access="!hasRole('ROLE_ADMIN')">
                                <li><a href="${pageContext.request.contextPath}/user/orders">Orders</a></li>
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
