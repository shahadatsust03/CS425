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
                            <li><a href="${pageContext.request.contextPath}/sections">Sections</a></li>
                            <li><a href="${pageContext.request.contextPath}/semesters">Semesters</a></li>
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
                            <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
                            <c:if test="${pageContext.request.userPrincipal.name == null}">
                              <li><a href="#testimonialsWrap">Login</a></li>
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <li><a href="<c:url value="j_spring_security_logout"/>">Logout</a></li>
                            </c:if>
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                              <li><a href="${pageContext.request.contextPath}/user/myaccount">My account</a></li>
                            </c:if>
                    </ul>
            </div><!--/.nav-collapse -->
    </div>
</div>

<div id="topWrap" class="jumbotron">
    <div style="text-align: center">
            <h4 style="color:#fff;margin:0;padding:0">${pageTitle}</h4>
      </div>
</div>
