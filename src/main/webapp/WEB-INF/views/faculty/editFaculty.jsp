<%-- 
    Document   : addProduct
    Created on : Aug 6, 2014, 4:36:16 PM
    Author     : Shahadat
--%>
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
                     <%@include file="faculty_navigation.jsp" %>
                    <div class="form-group">Faculty</div>
                          <form role="form" method="post" action="save">                               
                           <div class="form-group">
                             <label for="name">Name: </label>
                             <input type="text" class="form-control" id="name" placeholder="Name"  name="name"/>
                           </div>

                             <div class="form-group">
                             <label for="special">Speciality: </label>
                             <input type="text" class="form-control" id="special" placeholder="Speciality"  name="special"/>
                             </div>
                              
                             <div class="form-group">
                             <label for="special">Years of experience: </label>
                             <input type="number" class="form-control" id="exp" placeholder="Experience"  name="exp"/>
                             </div>
                              
                             <div class="form-group">
                             <label for="contactNum">Contact Number: </label>
                             <input type="number" class="form-control" id="contactNum" placeholder="Contact Number"  name="contactNum"/>
                             </div>
                              
                             <div class="form-group">
                             <label for="dateOfBirth">Date Of Birth: </label>
                             <input type="date" class="form-control" id="dateOfBirth" placeholder="Date Of Birth"  name="dateOfBirth"/>
                             </div>
                              
                              <div class="form-group">
                             <label for="email">Email: </label>
                             <input type="text" class="form-control" id="email" placeholder="Email "  name="email"/>
                           </div>
                           
                           <div class="form-group">
                             <label for="street">Street: </label>
                             <input type="text" class="form-control" id="street" placeholder="Street"  name="street"/>
                           </div>   
                           <div class="form-group">
                           <label for="city">City: </label>
                             <input type="text" class="form-control" id="city" placeholder="City"  name="city"/>
                           </div>   
                            <div class="form-group">
                           <label for="state">State: </label>
                             <input type="text" class="form-control" id="state" placeholder="State"  name="state"/>
                           </div>   
                           <div class="form-group">
                           <label for="country">Country: </label>
                             <input type="text" class="form-control" id="country" placeholder="Country"  name="country"/>
                           </div> 
              
                           <div class="form-group">
                           <label for="zip">Zip: </label>
                             <input type="text" class="form-control" id="zip" placeholder="zip"  name="zip"/>
                           </div> 
                            <div class="form-group">
                           <label for="username">User Name: </label>
                             <input type="text" class="form-control" id="username" placeholder="User Name"  name="username"/>
                           </div> 
                           <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
                           <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
            </div>>       
   
    </body>
</html>
