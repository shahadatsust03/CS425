<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <sec:authentication var="user" property="principal" />
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../header.jsp" %>
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
        <div id="featureWrap">
            <div class="container">
                ${message}
                    <div class="row">
                            <div class="col-sm-9 text-center feature">
                               <h3>My Advisees</h3>
			       <table class="table table-striped">    
					     <td>Name</td>
					     <td>Email</td>
					     <td>Phone</td>
					<c:forEach items="${advisees}" var="advisee">
					 <tr>      
					     <td><a href="${pageContext.request.contextPath}/user/users/${advisee.id}"/>${advisee.username}</td>
					     <td><a href="mailto:${advisee.email}">${advisee.email}</a></td>
					     <td>${advisee.contactNum}</td>
					 </tr>
				       </c:forEach>
				</table>
                            </div>
                            <div class="col-sm-3 text-center feature">
				    <h4></h4>
				    <p>
				      
					   <%@include file="../user/userprofile.jsp" %>
                                           <%@include file="navi.jsp" %>
				    </p>
                            </div>
                    </div>
            </div>
    </div> <!-- /featureWrap -->
    </body>
</html>
