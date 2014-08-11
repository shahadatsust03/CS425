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
            <c:if test="${empty Profile}">
                Profile Not Found.
            </c:if>
            <c:if test="${not empty Profile}">
                <c:if test="${not empty message}">
                    <b> ${message}</b>
                </c:if>

                <h1>My Profile!</h1>
                <table>
                    <tr><td><b>Full Name</b></td>       <td>${not empty Profile.fullname ? Profile.fullname :""}</td> </tr>
                    <tr><td><b>Email</b></td>     <td>${Profile.email}</td><td>(Use format name@company.com)</td></tr>
                    <tr><td><b>User Role</b></td>      <td>${Profile.AUTHORITY}</td> </tr>
                    <tr><td><b>Username</b></td>       <td>${Profile.username}</td> </tr>
                    <tr><td><b>Password</b></td>  <td>${Profile.password}</td></tr>
                    <tr><td><b>Date Of Birth</b></td> <td>${Profile.dateOfBirth}</td><td>(Use format yyyy-mm-dd)</td> </tr>
                    <tr><td><b>Joined Date</b></td> <td>${Profile.joinDate}</td><td>(Use format yyyy-mm-dd)</td> </tr>
                    <c:if test="${not empty Profile.contactNum}">
                        <tr><td><b>Contact Number</b></td>  <td>${Profile.contactNum}</td> </tr>
                    </c:if>
                    <tr><td><b>Street</b></td> <td>${Profile.street}</td> </tr>
                    <tr><td><b>City</b></td>   <td>${Profile.city}</td> </tr>
                    <tr><td><b>State</b></td>  <td>${Profile.state}</td> </tr>
                    <tr><td><b>Country</b></td> <td>${Profile.country}</td> </tr>
                    <tr><td><b>Zipcode</b></td> <td>${Profile.zipcode}</td> </tr>  
                </table>
            </c:if>
                <h5>
            <b>  <a href="${pageContext.request.contextPath}/user/editProfile/${Profile.id}">Edit Profile</a> </b>
        </h5>
            </div>
        </div> <!-- /featureWrap -->
        
    </body>
</html>
