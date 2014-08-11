<%-- 
    Document   : register_form
    Created on : Jul 30, 2014, 2:08:11 PM
    Author     : eTunkara
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form role="form" method="post" action="${pageContext.request.contextPath}/register">
        <div class="form-group">
          <label for="firstName">First Name</label>
          <input typ="text" class="form-control" id="firstName" name="firstName" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
        </div>
        <div class="form-group">
          <label for="lastName">Last Name</label>
          <input type="text" class="form-control" id="lastName" placeholder="Last Name"  name='lastName'/>
        </div>
        <div class="form-group">
          <label for="email">Email Address</label>
          <input type="email" class="form-control" id="email" placeholder="Email"  name='email'/>
        </div>
         <div class="form-group">
          <label for="username">Username</label>
          <input type="username" class="form-control" id="username" placeholder="Username"  name='username'/>
        </div>
        <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
        <button type="submit" class="btn btn-primary">Submit</button>
 </form>

