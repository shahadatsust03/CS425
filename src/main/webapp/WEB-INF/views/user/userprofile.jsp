<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
   <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h5>
                Welcome : ${user.username} |<a href="<c:url value="${pageContext.request.contextPath}/j_spring_security_logout" />" > Logout</a>
        </5>
        <div>
            
        </div>
    </c:if>
</div>