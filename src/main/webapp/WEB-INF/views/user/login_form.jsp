<%-- 
    Document   : login_form
    Created on : Jul 30, 2014, 1:49:56 PM
    Author     : eTunkara
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error eq true}">
            <p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        </c:if>
        
<form role="form" method="post" action="<c:url value='j_spring_security_check' />">
        <div class="form-group">
          <label for="username">Username</label>
<!--          <input type="text" class="form-control" id="username" name="name" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>-->
         <input type = "text" class="form-control" id="username" placeholder="Username" name="j_username" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/> <br />

        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" class="form-control" id="password" placeholder="Password"  name='j_password'/>
        </div>
        <div>
            <label for="remember">Remember me:</label>
            <input id="remember" type="checkbox" name="_spring_security_remember_me" /> <br />
        </div>

        <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
        <button type="submit" class="btn btn-primary" >Submit</button>
  </form>
