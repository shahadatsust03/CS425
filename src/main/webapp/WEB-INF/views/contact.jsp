<%@page import="org.springframework.util.StringUtils"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="header.jsp" %>
    </head>
    <body>
        <%@include file="home_nagivation.jsp" %>
        <div id="featureWrap">
            
            <div class="container">
                    <!--display flash message-->
                  
                    
              
                    <div class="row">
                            <div class="col-sm-4 text-center feature">
                                  <p>
                                      <h4>Lastest Classes</h4>
                                       <p>
                                          <%@include file="classes/class_list.jsp" %>
                                       </p>
                                    </p>
                            </div>
                            <div class="col-sm-4 text-center feature">
                                    <h4>Lastest Products</h4>
                                    <p>
                                       <%@include file="product/product_list.jsp" %>
                                    </p>
                            </div>
                                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                            
                            <div class="col-sm-4 text-center feature">                               
                                    <p>
                                          <ul class="nav nav-tabs" role="tablist">
                                            
         
                                          </ul>

                                         <!-- Tab panes -->
                                          <div class="tab-content">
                                              <div class="tab-pane active" id="login" style="padding:20px;text-align: left;">
                                                  <div>YogaStudio INC.</div>
                                                  <div>Maharishi University of Management </div>                                                  
                                                  <div>1000 N 4TH Street</div>                                                  
                                                  <div>Fairfield</div>                                                  
                                                  <div>IOWA</div>
                                                    
                                              </div>
                                              <!--<div class="tab-pane" id="register" style="padding:20px;text-align: left;">
                                              </div>
                                              -->
                                          </div>
                                    </p>
                                  
                                  <c:if test="${user != null}">
                                      <h3>User Profile</h3>
                                      ${pageContext.request.userPrincipal.name}
                                  </c:if>
                            </div>
                                    </c:if>
                    </div>
            </div>
    </div> <!-- /featureWrap -->
    </body>
</html>
