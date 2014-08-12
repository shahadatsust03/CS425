<%-- 
    Document   : register_form
    Created on : Jul 30, 2014, 2:08:11 PM
    Author     : eTunkara
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form role="form" method="post" action="register">
        <div class="form-group">
          <input typ="text" class="form-control" id="firstName" placeholder="First Name"   name="firstName" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" id="lastName" placeholder="Last Name"  name='lastName'/>
        </div>
        <div class="form-group">
          <input type="email" class="form-control" id="email" placeholder="Email"  name='email'/>
        </div>
         <div class="form-group">
          <input type="text" class="form-control" id="username" placeholder="Username"  name='username'/>
        </div>
         <div class="form-group">
          <input type="text" class="form-control" id="contactNum" placeholder="Contact number"  name='contactNum'/>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" id="street" placeholder="Street address"  name='street'/>
        </div>
         <div class="form-group">
          <input type="text" class="form-control" id="city" placeholder="City"  name='city'/>
        </div>
         <div class="form-group">
          <input type="text" class="form-control" id="state" placeholder="State"  name='state'/>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" id="country" placeholder="Country"  name='country'/>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" id="country" placeholder="Zipcode"  name='zipcode'/>
        </div>
        <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
        <button type="submit" class="btn btn-primary">Submit</button>
 </form>

