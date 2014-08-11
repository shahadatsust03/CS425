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
         <a href="#" id="forgetPassword" onclick="function request(){return false;}" data-toggle="modal" data-target="#password-request">Request Password</a>
      </form> 
         <!-- forget password -->
            <div class="modal fade" id="password-request" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">Request password</h4>
                  </div>
                  <div class="modal-body">
                      <div id="request-password-model-msg"></div>
                        <form role="form" method="post" action="${pageContext.request.contextPath}/requestpassword" id="request-password-form">
                          <div class="form-group">
                            <label for="request-email">Email</label>
                            <input type="email" class="form-control" id="request-email" placeholder="Email"  name="email"/>
                          </div>
                      </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="$(this).requestPassword('#request-password-form')">Submit</button>
                  </div>
                </div>
              </div>
            </div>

