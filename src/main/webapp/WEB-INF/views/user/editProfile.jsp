<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../header.jsp" %>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script>
                    $(function() {
                    $("#dateOfBirth").datepicker({ dateFormat: "yyyy-mm-dd" });
                    });        
        </script>
        <script type="text/javascript" language="JavaScript">
               function validate()
                            {
                            var vFullname = trim(document.frm.fullname.value);
                                    var vPassword = trim(document.getElementById('password').value);
                                    var vEmail = trim(document.getElementById('email').value);
                                    var vContactNum = trim(document.getElementById('contactNum').value)
                                    alert("enter for validation.Name:"+vFullname);
                                    if (vFullname == "")
                            {
                            alert("Full Name Field is Empty");
                                    
                                    return false;
                            }
                            else if (vPassword == "" || vPassword.length<6)
                            {
                            alert("Password Field is either Empty or not strong. Minimum 8 digits allowed.");
                                    
                                    return false;
                            }
                            else if (vEmail = "")
                            {
                            alert("Email Field is Empty");
                                    
                                    return false;
                            }
                            else if (vContactNum = "")
                            {
                            alert("Contact Number Field is Empty");
                                    
                                    return false;
                            }
                            else if (isAlpha(vFullname) == false)
                            {
                            alert("Full Name Field is not alpha character")
                                    
                                    return false;
                            }
                            else if (isDigits(vContactNum) == false)
                            {
                            alert("Contact Number Field is not numeric")
                                    
                                    return false;
                            }
                            else if (checkEmail(vEmail) == false)
                            {
                            alert("Email Format is not correct")
                                    //document.frm.vEmail.focus();
                                    return false;
                            }

                            }

                    function trim(s) {
                    return s.replace(/^s*/, "").replace(/s*$/, "");
                            }

                    function checkEmail(emailStr) {
                    if (emailStr.length == 0) {
                    return true;
                    }
                    var emailPat = /^(.+)@(.+)$/;
                            var specialChars = "\(\)<>@,;:\\".\[]";
                            var validChars = "[^\s" + specialChars + "]";
                            var quotedUser = "("[ ^ "]*")";
                                    var ipDomainPat = /^(d{1,3})[.](d{1,3})[.](d{1,3})[.](d{1,3})$/;
                                    var atom = validChars + "+";
                                    var word = "(" + atom + "|" + quotedUser + ")";
                                    var userPat = new RegExp("^" + word + "(\." + word + ")*$");
                                    var domainPat = new RegExp("^" + atom + "(\." + atom + ")*$");
                                    var matchArray = emailStr.match(emailPat);
                                    if (matchArray == null) {
                            return false;
                            }
                            var user = matchArray[1];
                                    var domain = matchArray[2];
                                    if (user.match(userPat) == null) {
                            return false;
                            }
                            var IPArray = domain.match(ipDomainPat);
                                    if (IPArray != null) {
                            for (var i = 1; i <= 4; i++) {
                            if (IPArray[i] > 255) {
                            return false;
                            }
                            }
                            return true;
                            }
                            var domainArray = domain.match(domainPat);
                                    if (domainArray == null) {
                            return false;
                            }
                            var atomPat = new RegExp(atom, "g");
                                    var domArr = domain.match(atomPat);
                                    var len = domArr.length;
                                    if ((domArr[domArr.length - 1].length < 2) ||
                                            (domArr[domArr.length - 1].length > 3)) {
                            return false;
                            }
                            if (len < 2) {
                            return false;
                            }
                            return true;
                                    }
                            function isAlpha(argvalue) {
                            argvalue = argvalue.toString();
                                    var validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                    for (var n = 0; n < argvalue.length; n++) {
                            if (validChars.indexOf(argvalue.substring(n, n + 1)) == - 1)
                                    return false;
                            }
                            return true;
                                    }
                            function isDigits(argvalue) {
                            argvalue = argvalue.toString();
                                    var validChars = "0123456789";
                                    var startFrom = 0;
                                    if (argvalue.substring(0, 2) == "0x") {
                            validChars = "0123456789abcdefABCDEF";
                                    startFrom = 2;
                            } else if (argvalue.charAt(0) == "0") {
                            validChars = "01234567";
                                    startFrom = 1;
                            }
                            for (var n = 0; n < argvalue.length; n++) {
                            if (validChars.indexOf(argvalue.substring(n, n + 1)) == - 1) return false;
                            }
                            return true;
                                    }

        </script>        
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
        <div id="featureWrap">
            <div class="container">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">My Profile</div>
                    <div class="panel-body">
                        <c:if test="${empty Profile}">
                            Profile Not Found.
                        </c:if>
                        <c:if test="${not empty Profile}">
                            <form name="frm" onsubmit="return validate()" role="form" action="../../user/editProfile/${Profile.id}" method="post">
                                <table>
                                    <tr><td><b>Full Name:</b></td><td><input type="text" name="fullname" value="${not empty Profile.fullname ? Profile.fullname :""}"></td></tr>

                                    <tr><td><b>Email</b></td>     <td><input type="text" name="email" value="${not empty Profile.email ? Profile.email:""}"></td><td>(Use format name@company.com)</td> </tr>
                                   <!-- <tr><td>User Role</td>       <td><input type="label" name="AUTHORITY" value="${Profile.AUTHORITY}"</td> </tr> -->
                                    <tr><td><b>Username</b></td>      <td>${not empty Profile.username ? Profile.username : ""}</td> </tr>
                                    <tr><td><b>Password</b></td>  <td><input type="text" name="password" value="${not empty Profile.password ? Profile.password : ""}"></td></tr>
                                    <tr><td><b>Date Of Birth</b></td> <td><input type="date" name="dateOfBirth" value="${not empty Profile.dateOfBirth ? Profile.dateOfBirth : ""}"></td><td>(Format: yyyy-mm-dd)</td> </tr>
                                    <tr><td><b>Contact Number</b></td>  <td><input type="text" name="contactNum" value="${not empty Profile.contactNum ? Profile.contactNum : ""}"></td> </tr>
                                    <tr><td><b>Street</b></td>  <td><input type="text" name="street" value="${not empty Profile.street ? Profile.street : ""}"></td> </tr>
                                    <tr><td><b>City</b></td> <td><input type="text" name="city" value="${not empty Profile.city ? Profile.city : ""}"></td> </tr>
                                    <tr><td><b>State</b></td>  <td><input type="text" name="state" value="${not empty Profile.state ? Profile.state : ""}"></td> </tr>
                                    <tr><td><b>Country</b></td> <td><input type="text" name="country" value="${not empty Profile.country ? Profile.country : ""}"></td> </tr>
                                    <tr><td><b>Zipcode</b></td> <td><input type="text" name="zipcode" value="${not empty Profile.zipcode ? Profile.zipcode : ""}"></td> </tr>  
                                </table>
                                <div><br/>
                                    <a  class="btn btn-primary" type="button" href="../../user/myaccount" value="Cancel">Cancel</a>
                                    <button  class="btn btn-primary" type="submit" value="Save Profile">Save</button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <script language="JavaScript">
                                    function openpopup(anchor){
                                    var popurl = "classes/classPopup" + "#" + anchor;
                                            popup_window = window.open(popurl, "", "width=600,height=400,");
                                            }
                            function updateValue(value)
                                    {    // this gets called from the popup window and updates the field with a new value
                                    var elem = document.getElementById("prerequestie");
                                            elem.value = value;
                                            }

        </script>
    </div> <!-- /featureWrap -->
    <!--    <h5>
                                <td><a href="${pageContext.request.contextPath}/user/myaccount/${Profile}">Save Profile</a></td>-->
</body>
</html>
