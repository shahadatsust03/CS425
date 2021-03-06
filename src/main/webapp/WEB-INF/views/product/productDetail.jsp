<%-- 
    Document   : addProduct
    Created on : Aug 6, 2014, 4:36:16 PM
    Author     : eTunkara
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
                            <h4>${product.name}</h4>
                        </div>
                        <div class="panel-body">
                            <img src="${pageContext.request.contextPath}/products/image/${product.getFirstImage().getId()}" alt="${product.name}" style="width:200px;"/>
                         <div>Description: ${product.description}</div>
                         <div>Price: ${product.price}</div>
                         <div>   
     
                             <button 
                                 onclick="simpleCart.add('name=${product.name}','price=${product.price}','image=${pageContext.request.contextPath}/products/image/${product.getFirstImage().getId()}');" type="button" class="btn btn-success btn-primary btn-xs">
                                 Add to order
                             </button>
                         </div>
                        </div>
                        
                    
                    </div>
                </div>
    </div> 
    </body>
</html>
