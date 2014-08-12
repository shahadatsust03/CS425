<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <h3 class="panel-title">My Profile!</h3>
                    </div>
                    <div class="panel-body">
                         <c:if test="${empty Profile}">
                            Profile Not Found.
                        </c:if>
                                     
                        <c:if test="${not empty Profile}">
                              <c:if test="${not empty message}">
                                  <b> ${message}</b>
                              </c:if>
                                  <div style="display: inline-block;vertical-align: top;width:70%">
                                    <table>
                                        <tr><td><b>Full Name</b></td>       <td>${not empty Profile.fullname ? Profile.fullname :""}</td> </tr>
                                        <tr><td><b>Email</b></td>     <td>${not empty Profile.email ? Profile.email:""}</td></tr>
                                        <tr><td><b>User Role</b></td>      <td>${not empty Profile.AUTHORITY? Profile.AUTHORITY : ""}</td> </tr>
                                        <tr><td><b>Date Of Birth</b></td> <td>${not empty Profile.dateOfBirth ? Profile.dateOfBirth : ""}</td> </tr>
                                        <tr><td><b>Joined Date</b></td> <td>${not empty Profile.joinDate ? Profile.joinDate : ""}</td><td>(format yyyy-mm-dd)</td> </tr>

                                            <tr><td><b>Contact Number</b></td>  <td>${not empty Profile.contactNum ? Profile.contactNum : ""}</td> </tr>

                                        <tr><td><b>Street</b></td> <td>${not empty Profile.street ? Profile.street : ""}</td> </tr>
                                        <tr><td><b>City</b></td>   <td>${not empty Profile.city ? Profile.city : ""}</td> </tr>
                                        <tr><td><b>State</b></td>  <td>${not empty Profile.state ? Profile.state : ""}</td> </tr>
                                        <tr><td><b>Country</b></td> <td>${not empty Profile.country ? Profile.country : ""}</td> </tr>
                                        <tr><td><b>Zipcode</b></td> <td>${not empty Profile.zipcode ? Profile.zipcode : ""}</td> </tr>  
                                    </table>
                                 </div>
                                 <div style="display: inline-block;vertical-align: top;">
                                     <span>Billing information</span>
                                     <button class="btn btn-small btn-primary  btn-xs" type="button" 
                                        data-toggle="modal" data-target="#creditcard-modal"
                                        >
                                        Add Credit
                                     </button>
                                      <!--credit card model -->
                                            <div class="modal fade" id="creditcard-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                              <div class="modal-dialog">
                                                <div class="modal-content">
                                                  <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                    <h4 class="modal-title" id="myModalLabel">New Credit card</h4>
                                                  </div>
                                                  <div class="modal-body">
                                                      <div id="credi-card-model-msg">
                                                       <form id="" role="form" action="${pageContext.request.contextPath}/user/savecreditcard">
                                                                <div class="form-group">
                                                                  <label for="cardnumber">Card number</label>
                                                                  <input type="text" class="form-control" id="cardnumber" name="cardnumber" placeholder="Enter card number">
                                                                </div>
                                                                <div class="form-group">
                                                                  <label for="expirydate">Expiry date</label>
                                                                  <input type="text" class="form-control" id="expirydate"  name="expirydate" placeholder="Enter expiry date">
                                                                </div>                                                           
                                                           </form>
                                                  </div>
                                                  <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    <button type="button" class="btn btn-primary" onclick="$(this).saveCreditCard('#request-password-form')">Submit</button>
                                                  </div>
                                                </div>
                                              </div>
                                            </div>
                                         </div>
                                 </div>
                               <h5>
                                    <b>  <a class="btn btn-primary" type="button" href="${pageContext.request.contextPath}/user/editProfile/${Profile.id}">Edit Profile</a> </b>
                              </h5>
                          </c:if>
                    </div>
                      
                  </div>   

            </div>
        </div> <!-- /featureWrap -->
        <script>
            $(document).ready(function() {   
                   var content = '<form role="form"><div class="form-group">\n'
                                      '<label for="exampleInputEmail1">Email address</label>\n'
                                      '<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">\n'
                                    '</div>\n'
                                   ' <div class="form-group">\n'
                                      '<label for="exampleInputPassword1">Password</label>\n'
                                      '<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">\n'+
                                    '</div>\n'
                                    '<button type="submit" class="btn btn-default">Submit</button>\n'
                                 ' </form>';
                         
                   $("#addCredit").popover({content: content,html:true});
            });
         
        </script>
    </body>
</html>
