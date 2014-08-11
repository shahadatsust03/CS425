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
                        <div class="panel-heading" style="border:none;background:none;">
                            <h4>${section.sectionName}
                            </h4>
                        </div>
                        <div class="panel-body">
                            <table>
                                <tr><td>Description : </td><td>${section.description}</td>
                                <tr><td>Location: </td><td>${section.location}</div></td> 
                                <tr><td>Class Limit: </td><td>${section.classLimit}</div></td>
                            </table>
                        </div>  

                        <a href="${pageContext.request.contextPath}/processenrollment/${section.id}"> <button type="button" class="btn btn-primary">Enroll</button>    </a>  
                </div>

            </div>
        </div> 
    </body>
</html>
