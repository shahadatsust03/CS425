<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
    </head>
    <body>
        <h1>User List</h1>
        
                <table>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
<!--                    <td>${user.password}</td>-->
                    <td>${user.fullname}</td>
                    <td>${user.email}</td>
                    <td>${user.activests}</td>
                    <td>${user.AUTHORITY}</td>
                    <td>${user.userranked}</td>
                    <td>${user.userlabel}</td>
                    <td>${user.userrating}</td>
                    
                    
                    <sec:authorize access="hasRole('ROLE_ADMIN')" >
                        <td><a href="users/${user.id}">edit</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>

        <sec:authorize url="/addUser" >
            <a href="addUser"> Add a User</a>
        </sec:authorize>
        <a href="j_spring_security_logout">logout</a>

        
    </body>
</html>