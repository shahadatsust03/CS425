<%-- 
    Document   : profile
    Created on : Aug 6, 2014, 3:12:24 PM
    Author     : demodem
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
    </head>
    <body>
        <h1>My Profile!</h1>
        <table>
            <tr><td>Full Name</td>       <td>${Profile.fullname}</td> </tr>
            <tr><td>Email</td>       <td>${Profile.email}</td> </tr>
            <tr><td>User Role</td>       <td>${Profile.AUTHORITY}</td> </tr>
            <tr><td>Username</td>       <td>${Profile.username}</td> </tr>
            <tr><td>Full Name</td>  <td>${Profile.password}</td></tr>
            <tr><td>Date Of Birth</td> <td>${Profile.dateOfBirth}</td> </tr>
            <tr><td>Joined Date</td>  <td>${Profile.joinDate}</td> </tr>
            <tr><td>Contact Number</td>  <td>${Profile.contactNum}</td> </tr>
            <tr><td>Street</td>  <td>${Profile.street}</td> </tr>
            <tr><td>City</td>  <td>${Profile.city}</td> </tr>
            <tr><td>State</td>  <td>${Profile.state}</td> </tr>
            <tr><td>Country</td> <td>${Profile.country}</td> </tr>
            <tr><td>Zipcode</td> <td>${Profile.zipcode}</td> </tr>  
        </table>
    </body>
</html>
