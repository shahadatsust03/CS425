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
                        <h4>${product.name}
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div style="display: inline-table">
                            <img src="${pageContext.request.contextPath}/products/image/${product.getFirstImage().getId()}" alt="${product.name}" style="width:200px;"/>
                        </div>
                        <div  style="display: inline-table;vertical-align: top;margin-left:20px;">
                            <div>Description: ${product.description}</div>
                            <div>Price: ${product.price}</div>
                            <div>Units: ${product.numberOfProducts}</div>
                        </div>
                    </div>
                    </div>
                    <h5>
                        <td><a  class="btn btn-small btn-primary  btn-xs" href="${pageContext.request.contextPath}/product/edit_product/${product.id}">Edit Product</a></td>
                        <td><a  class="btn btn-small btn-primary  btn-xs" href="${pageContext.request.contextPath}/product/remove/${product.id}">Remove Product</a></td>


                </div>
            </div> 
    </body>
</html>
