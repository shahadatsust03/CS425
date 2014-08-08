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
                        
                        <form action="../../product/save/${product.id}" method="post">
                            <div>
                            <br/>Product Name:<input type="text" name="name" value="${product.name}">
                            </div>
                            <div><br/>Description:<input type="text" name="description" value="${product.description}">                             
                            </div>
                            <div><br/>Description:<input type="text" name="price" value="${product.price}"> 
                            </div>
                            <div><br/>Units: <input type="text" name="numberOfProducts" value="${product.numberOfProducts}"> 
                            </div>
                            </div>
                           
                            <div><br/><input type="submit" value="Submit">
                        </form>
                    </div>
                </div>
    </div> 
    </body>
</html>
