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
                  <div class="row">
                     <div class="col-md-4" style="width:20%">
                         <ul class="nav nav-pills nav-stacked">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Messages</a></li>
                         </ul>
                       </div>
                        <div class="col-md-8">

                                       <c:if test="${empty Profile}">
                                          Profile Not Found.
                                      </c:if>

                                      <c:if test="${not empty Profile}">
                                            <c:if test="${not empty message}">
                                                <b> ${message}</b>
                                            </c:if>
                                            <div class="panel panel-default">
                                              <div class="panel-body">
                                                  <div>
                                                    <h3 class="panel-title">My Profile!</h3>
                                                  <table>
                                                      <tr><td><b>Full Name</b></td>       <td>${not empty Profile.fullname ? Profile.fullname :""}</td> </tr>
                                                      <tr><td><b>Email</b></td>     <td>${not empty Profile.email ? Profile.email:""}</td></tr>                        
                                                      <tr><td><b>Date Of Birth</b></td> <td>${not empty Profile.dateOfBirth ? Profile.dateOfBirth : ""}</td> </tr>
                                                      <tr><td><b>Joined Date</b></td> <td>${not empty Profile.joinDate ? Profile.joinDate : ""}</td><td></td> </tr>

                                                          <tr><td><b>Contact Number</b></td>  <td>${not empty Profile.contactNum ? Profile.contactNum : ""}</td> </tr>

                                                          <c:if test="${not empty Profile.creditCard}">
                                                          <tr><td><b>Credit Card Number</b></td> <td>${not empty Profile.creditCard.cardNumber ? Profile.creditCard.cardNumber : ""}</td></tr>
                                                          <!-- <tr><td><b>Expiry Date:</b></td> <td>${not empty Profile.creditCard.expiryDate ? Profile.creditCard.expiryDate : ""}</td></tr> -->
                                                          </c:if>
                                                      <tr><td><b>Street</b></td> <td>${not empty Profile.street ? Profile.street : ""}</td> </tr>
                                                      <tr><td><b>City</b></td>   <td>${not empty Profile.city ? Profile.city : ""}</td> </tr>
                                                      <tr><td><b>State</b></td>  <td>${not empty Profile.state ? Profile.state : ""}</td> </tr>
                                                      <tr><td><b>Country</b></td> <td>${not empty Profile.country ? Profile.country : ""}</td> </tr>
                                                      <tr><td><b>Zipcode</b></td> <td>${not empty Profile.zipcode ? Profile.zipcode : ""}</td> </tr>  
                                                  </table>
                                             <h5>
                                                  <b>  <a class="btn btn-small btn-primary  btn-xs" type="button" href="${pageContext.request.contextPath}/user/editProfile/${Profile.id}">Edit Profile</a> </b>
                                            </h5>
                                                </div>                                                
                                             </div>
                                            
                                            <ul class="list-group">
                                                   <li class="list-group-item">
                                                       <h4 class="list-group-item-heading">Billing information</h4>
                                                   <button class="btn btn-small btn-primary  btn-xs" type="button" 
                                                      data-toggle="modal" data-target="#creditcard-modal"
                                                      >
                                                      Add Credit
                                                   </button>
                                                     <!--show user credit cards -->
                                                    <div>
                                                        
                                                        <table class="table table-striped">
                                                            <tr>
                                                                <td>
                                                                  ${Profile.getCreditCard().getCardNumber()}
                                                                </td>
                                                                <td>
                                                                   ${Profile.getCreditCard().getExpiryDate()}
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!--credit card model -->
                                                          <div class="modal fade" id="creditcard-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                              <div class="modal-content">
                                                                <div class="modal-header">
                                                                  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                                  <h4 class="modal-title" id="myModalLabel">New Credit card</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div id="credit-card-model-msg"> </div>
                                                                     <form id="newCreditCard" role="form" action="${pageContext.request.contextPath}/user/savecreditcard">
                                                                              <div class="form-group">
                                                                                <label for="cardnumber">Card number</label>
                                                                                <input type="text" class="form-control" id="cardnumber" name="cardnumber" placeholder="Enter card number">
                                                                              </div>
                                                                              <div class="form-group">
                                                                                <label for="expirydate">Expiry date</label>
                                                                                <input type="text" class="form-control" id="expirydate"  name="expirydate" placeholder="Enter expiry date"><div>Format: MM/YYYY</div>
                                                                              </div>                                                           
                                                                         </form>
                                                                </div>
                                                                <div class="modal-footer">
                                                                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                  <button type="button" class="btn btn-primary" onclick="$(this).saveCreditCard('#newCreditCard')">Submit</button>
                                                                </div>
                                                              </div>
                                                            </div>
                                                          </div>
                                                       </div>
                                                            
                                                       </li>
                                                   </ul>
                                            </div>
                                       </c:if>                                                           
                                </div>   
                        </div>
                  </div>
            </div>
        </div> <!-- /featureWrap -->
    </body>
</html>
