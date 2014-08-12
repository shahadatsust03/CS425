<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <sec:authentication var="user" property="principal" />
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
                  <%@include file="class_navigation.jsp" %>
                  <%@include file="main_class_list.jsp" %>
                  
            </div>
    </div> 
    </body>
</html>