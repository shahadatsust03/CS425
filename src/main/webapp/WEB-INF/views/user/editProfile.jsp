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
            <c:if test="${empty Profile}">
                Profile Not Found.
            </c:if>
            <c:if test="${not empty Profile}">
                <form action="../../user/editProfile/${Profile.id}" method="post">
                    <h1>My Profile!</h1>
                    <table align="center">
                        <tr><td><b>Full Name:</b></td><td><input type="text" name="fullname" value="${not empty Profile.fullname ? Profile.fullname :""}"></td></tr>

                        <tr><td><b>Email</b></td>     <td><input type="text" name="email" value="${not empty Profile.email ? Profile.email:""}"></td><td>(Use format name@company.com)</td> </tr>
                       <!-- <tr><td>User Role</td>       <td><input type="label" name="AUTHORITY" value="${Profile.AUTHORITY}"</td> </tr> -->
                        <tr><td><b>Username</b></td>      <td><input type="text" name="username" value="${not empty Profile.username ? Profile.username : ""}"></td> </tr>
                        <tr><td><b>Full Name</b></td>  <td><input type="text" name="password" value="${not empty Profile.password ? Profile.password : ""}"></td></tr>
                        <tr><td><b>Date Of Birth</b></td> <td><input type="date" name="dateOfBirth" value="${not empty Profile.dateOfBirth ? Profile.dateOfBirth : ""}"></td><td></td> </tr>
                        <tr><td><b>Contact Number</b></td>  <td><input type="text" name="contactNum" value="${not empty Profile.contactNum ? Profile.contactNum : ""}"></td> </tr>
                        <tr><td><b>Street</b></td>  <td><input type="text" name="street" value="${not empty Profile.street ? Profile.street : ""}"></td> </tr>
                        <tr><td><b>City</b></td> <td><input type="text" name="city" value="${not empty Profile.city ? Profile.city : ""}"></td> </tr>
                        <tr><td><b>State</b></td>  <td><input type="text" name="state" value="${not empty Profile.state ? Profile.state : ""}"></td> </tr>
                        <tr><td><b>Country</b></td> <td><input type="text" name="country" value="${not empty Profile.country ? Profile.country : ""}"></td> </tr>
                        <tr><td><b>Zipcode</b></td> <td><input type="text" name="zipcode" value="${not empty Profile.zipcode ? Profile.zipcode : ""}"></td> </tr>  
                    </table>
                </c:if>
                <div align="center"><br/><input type="submit" value="Save Profile">
                    </form>
                </div>
        </div> <!-- /featureWrap -->
        <!--    <h5>
                                    <td><a href="${pageContext.request.contextPath}/user/myaccount/${Profile}">Save Profile</a></td>-->
    </body>
</html>
