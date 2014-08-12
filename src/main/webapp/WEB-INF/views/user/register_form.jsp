<%-- 
    Document   : register_form
    Created on : Jul 30, 2014, 2:08:11 PM
    Author     : eTunkara
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form role="form"   class="form-horizontal" method="post" action="${pageContext.request.contextPath}/register" id="register-customer-form">
        <div class="form-group">
          <label class="sr-only" for="firstName" class="col-sm-2 control-label">First Name</label>
         <div class="col-sm-10">
          <input typ="text" class="form-control" id="firstName" placeholder="First Name"   name="firstName" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
        </div>
        </div>
        <div class="form-group">
           <label class="sr-only" for="lastName" class="col-sm-2 control-label">Last Name</label>
         <div class="col-sm-10">
          <input type="text" class="form-control" id="lastName" placeholder="Last Name"  name='lastName'/>
          </div>
        </div>
        <div class="form-group">
          <label class="sr-only" for="email" class="col-sm-2 control-label">Email</label>
          <div class="col-sm-10">
            <input type="email" class="form-control" id="email" placeholder="Email"  name='email'/>
          </div>
        </div>
         <div class="form-group">
             <label class="sr-only" for="username" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
             <input type="text" class="form-control" id="username" placeholder="Username"  name='username'/>
            </div>
        </div>
         <div class="form-group">
          <label class="sr-only" for="contactNum" class="col-sm-2 control-label">Phone Number</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="contactNum" placeholder="Contact number"  name='contactNum'/>
          </div>
        </div>
        <div class="form-group">
           <label class="sr-only" for="street" class="col-sm-2 control-label">Street </label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="street" placeholder="Street address"  name='street'/>
          </div>
        </div>
         <div class="form-group">
           <label class="sr-only" for="city" class="col-sm-2 control-label">City</label>
           <div class="col-sm-10">
             <input type="text" class="form-control" id="city" placeholder="City"  name='city'/>
           </div>
        </div>
         <div class="form-group">
           <label class="sr-only" for="state" class="col-sm-2 control-label">Street </label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="state" placeholder="State"  name='state'/>
          </div>
        </div>
        <div class="form-group">
           <label class="sr-only" for="country" class="col-sm-2 control-label">Country </label>  
           <div class="col-sm-10">
            <input type="text" class="form-control" id="country" placeholder="Country"  name='country'/>
           </div>
        </div>
        <div class="form-group">
           <label class="sr-only" for="zipcode" class="col-sm-2 control-label">Zip Code </label>
          <div class="col-sm-10">
           <input type="text" class="form-control" id="country" placeholder="Zipcode"  name='zipcode'/>
          </div>
        </div>
        <div class="form-group">
           <label class="sr-only" for="dateOfBirth" class="col-sm-2 control-label">Date of Birth </label>
          <div class="col-sm-10">
           <input type="text" class="form-control" id="dateOfBirth" placeholder="Date of Birth"  name='dateOfBirth'/>
          </div>
        </div>
        <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
 </form>

<script type="text/javascript">
            // When the document is ready
          /*  $(document).ready(function () {
                
                $('#dateofBirth').datepicker({
                    format: "dd/mm/yyyy"
                });  
            
            });
            */
        </script>
