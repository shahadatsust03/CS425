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
        <script type="text/javascript" language="JavaScript">
            function validate()
            {                
                var vFullname = document.getElementById('fullname');
                var vPassword = document.getElementById('password');
                var vEmail = document.getElementById('email');
                var vContactNum = document.getElementById('contactNum');
               // alert("validating Name:" + vFullname.value);
                if (vFullname.value == '')
                {
                    alert("Full Name Field is Empty");

                    return false;
                }
                else if (vPassword.value == '' || vPassword.length < 6)
                {
                    alert("Password Field is either Empty or not strong. Minimum 8 digits allowed.");

                    return false;
                }
//                else if (vEmail.value == '')
//                {
//                    alert("Email Field is Empty");
//
//                    return false;
//                }
//                else if (vContactNum.value == '')
//                {
//                    alert("Contact Number Field is Empty");
//
//                    return false;
//                }
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
                                    <tr><td><b>Full Name:</b></td><td><input id="fullname" type="text" name="fullname" value="${not empty Profile.fullname ? Profile.fullname :""}"></td></tr>

                                    <tr><td><b>Email</b></td>     <td><input id="email" type="text" name="email" value="${not empty Profile.email ? Profile.email:""}"></td><td>(Use format name@company.com)</td> </tr>
                                   <!-- <tr><td>User Role</td>       <td><input type="label" name="AUTHORITY" value="${Profile.AUTHORITY}"</td> </tr> -->
                                    <tr><td><b>Username</b></td>      <td>${not empty Profile.username ? Profile.username : ""}</td> </tr>
                                    <tr><td><b>Password</b></td>  <td><input id="password" type="text" name="password" value="${not empty Profile.password ? Profile.password : ""}"></td></tr>
                                    <tr><td><b>Date Of Birth</b></td> <td><input type="date" name="dateOfBirth" value="${not empty Profile.dateOfBirth ? Profile.dateOfBirth : ""}"></td><td>(Format: yyyy-mm-dd)</td> </tr>
                                    <tr><td><b>Contact Number</b></td>  <td><input id="contactNum" type="text" name="contactNum" value="${not empty Profile.contactNum ? Profile.contactNum : ""}"></td> </tr>
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
    </div> <!-- /featureWrap -->
    <!--    <h5>
                                <td><a href="${pageContext.request.contextPath}/user/myaccount/${Profile}">Save Profile</a></td>-->
</body>
</html>
