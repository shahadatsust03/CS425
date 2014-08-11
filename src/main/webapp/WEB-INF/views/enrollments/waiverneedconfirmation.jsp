<%-- 
    Document   : addProduct
    Created on : Aug 6, 2014, 4:36:16 PM
    Author     : Shahadat
--%>
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
                <div class="panel panel-default">                    
                    <h3> You donnot have the prerequiste for ${class.className}. Do you want to request a waiver? </h3>

                        <a href="${pageContext.request.contextPath}/enrollments"> <button type="button" class="btn btn-primary">Yes</button>    </a>  
                        <a href="${pageContext.request.contextPath}/enrollments"> <button type="button" class="btn btn-primary">No</button>    </a> 
                </div>

            </div>
        </div> 
    </body>
</html>
