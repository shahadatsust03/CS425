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
                            <h4>${sections.sectionName}
                            </h4>
                        </div>
                        <div class="panel-body">
                         <div>Description: ${sections.description}</div>
                         <div>Price: ${sections.location}</div>
                         <div>Price: ${sections.fee}</div>
                         <div>Prerequesties: 
                         <c:if test="${not empty classes.prerequisteClasses}">
                             <c:forEach items="${classes.prerequisteClasses}" var="preReq">                                 
                                 ${classes.prerequisteClasse.className} &nbsp;&nbsp;
                             </c:forEach>
                         </c:if>
                         </div>
                        </div>
                    </div>
                </div>
    </div> 
    </body>
</html>
