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

        <ul class="list-group">
            <li class="list-group-item">
                <h4 class="list-group-item-heading">Billing information</h4>

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
                                    <button class="btn btn-small btn-primary  btn-xs" type="button" onclick="" >
                                        Remove credit card
                                    </button>                                       
                                </c:if>     
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
            </li>
        </ul>

    </div>
</c:if>