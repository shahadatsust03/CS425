<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="header.jsp" %>

    </head>   
    <body>
        <fieldset style="width: 40%; position: absolute;"><legend><h3>Login Page!</h3></legend>             
               <%@include file="login_form.jsp" %>
            </fieldset>
    </body>
</html>
