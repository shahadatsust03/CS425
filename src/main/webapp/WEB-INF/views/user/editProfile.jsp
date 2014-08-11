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
                    <table>
                        <tr><td><b>Full Name:</b></td><td><input type="text" name="fullname" value="${Profile.fullname}"></td></tr>

                        <tr><td><b>Email</b></td>     <td><input type="text" name="email" value="${Profile.email}"></td><td>(Use format name@company.com)</td> </tr>
                       <!-- <tr><td>User Role</td>       <td><input type="label" name="AUTHORITY" value="${Profile.AUTHORITY}"</td> </tr> -->
                        <tr><td><b>Username</b></td>      <td><input type="text" name="username" value="${Profile.username}"></td> </tr>
                        <tr><td><b>Full Name</b></td>  <td><input type="text" name="password" value="${Profile.password}"></td></tr>
                        <tr><td><b>Date Of Birth</b></td> <td><input type="date" name="dateOfBirth" value="${Profile.dateOfBirth}"></td><td></td> </tr>
                        <tr><td><b>Contact Number</b></td>  <td><input type="text" name="contactNum" value="${Profile.contactNum}"></td> </tr>
                        <tr><td><b>Street</b></td>  <td><input type="text" name="street" value="${Profile.street}"></td> </tr>
                        <tr><td><b>City</b></td> <td><input type="text" name="city" value="${Profile.city}"></td> </tr>
                        <tr><td><b>State</b></td>  <td><input type="text" name="state" value="${Profile.state}"></td> </tr>
                        <tr><td><b>Country</b></td> <td><input type="text" name="country" value="${Profile.country}"></td> </tr>
                        <tr><td><b>Zipcode</b></td> <td><input type="text" name="zipcode" value="${Profile.zipcode}"></td> </tr>  
                    </table>
                </c:if>
                <div><br/><input type="submit" value="Save Profile">
                    </form>
                </div>
        </div> <!-- /featureWrap -->
        <!--    <h5>
                                    <td><a href="${pageContext.request.contextPath}/user/myaccount/${Profile}">Save Profile</a></td>-->
    </body>
</html>
