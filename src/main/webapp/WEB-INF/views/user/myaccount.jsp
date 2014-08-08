<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../header.jsp" %>
    </head>
    <body>
        <div id="featureWrap">   
            <c:if test="${empty Profile}">
        Profile Not Found.
        </c:if>
        <c:if test="${not empty Profile}">
        <h1>My Profile!</h1>
        <table>
            <tr><td>Full Name</td>       <td>${Profile.username}</td> </tr>
            <tr><td>Email</td>       <td>${Profile.email}</td> </tr>
            <tr><td>User Role</td>       <td>${Profile.AUTHORITY}</td> </tr>
            <tr><td>Username</td>       <td>${Profile.username}</td> </tr>
            <tr><td>Full Name</td>  <td>${Profile.password}</td></tr>
            <tr><td>Date Of Birth</td> <td>${Profile.dateOfBirth}</td> </tr>
            <tr><td>Joined Date</td>  <td>${Profile.joinDate}</td> </tr>
            <c:if test="${not empty Profile.contactNum}">
                <tr><td>Contact Number</td>  <td>${Profile.contactNum}</td> </tr>
            </c:if>
            <tr><td>Street</td>  <td>${Profile.street}</td> </tr>
            <tr><td>City</td>  <td>${Profile.city}</td> </tr>
            <tr><td>State</td>  <td>${Profile.state}</td> </tr>
            <tr><td>Country</td> <td>${Profile.country}</td> </tr>
            <tr><td>Zipcode</td> <td>${Profile.zipcode}</td> </tr>  
        </table>
        </c:if>
    </div> <!-- /featureWrap -->
    </body>
</html>
