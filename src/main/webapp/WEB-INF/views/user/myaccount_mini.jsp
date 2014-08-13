<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>           
<c:if test="${not empty Profile}">                               
    <div class="panel panel-default">
        <div class="panel-body"> 
            <table>
                <tr><td><b>Full Name</b></td>       <td>${not empty Profile.fullname ? Profile.fullname :""}</td> </tr>
                <tr><td><b>Email</b></td>     <td>${not empty Profile.email ? Profile.email:""}</td></tr>                        
                <tr><td><b>Date Of Birth</b></td> <td>${not empty Profile.dateOfBirth ? Profile.dateOfBirth : ""}</td> </tr>
                <tr><td><b>Joined Date</b></td> <td>${not empty Profile.joinDate ? Profile.joinDate : ""}</td><td></td> </tr>

                <tr><td><b>Contact Number</b></td>  <td>${ Profile.contactNum }</td> </tr>
                <tr><td><b>Street</b></td> <td>${not empty Profile.street ? Profile.street : ""}</td> </tr>
                <tr><td><b>City</b></td>   <td>${not empty Profile.city ? Profile.city : ""}</td> </tr>
                <tr><td><b>State</b></td>  <td>${not empty Profile.state ? Profile.state : ""}</td> </tr>
                <tr><td><b>Country</b></td> <td>${not empty Profile.country ? Profile.country : ""}</td> </tr>
            </table>
        </div>   
    <sec:authorize access="!hasRole('ROLE_ADMIN')">   
        <ul class="list-group">
            <li class="list-group-item">
                <div>
                    <div style="display:inline-block">
                       <h4 class="list-group-item-heading">Billing information</h4>
                    </div>
                    <div  style="display:inline-block;margin-left: 30px">
                        <c:if test="${empty Profile.getCreditCard().getCardNumber()}">                  
                               <button class="btn btn-small btn-primary  btn-xs" type="button"
                                      onclick="function register(){return false;}" data-toggle="modal" data-target="#creditcard-modal"
                                      >
                                 Add credit card
                               </button>                                       
                         </c:if> 
                    </div>
                </div>
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
                            <td>
                                <c:if test="${not empty Profile.getCreditCard().getCardNumber()}">                  
                                    <button class="btn btn-small btn-primary  btn-xs" type="button" 
                                            onclick="$(this).removeCard()" >
                                        Remove credit card
                                    </button>                                       
                                </c:if>     
                            </td>
                        </tr>
                    </table>
                </div>
               
            </li>
        </ul>
    </sec:authorize>
    </div>
</c:if>