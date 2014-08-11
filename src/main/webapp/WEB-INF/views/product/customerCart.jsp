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
                            <h4>My Cart
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div id="cartMsg"></div>
                            <!--Add a Div with the class "simpleCart_items" to show your shopping cart area.-->
			     <div class="simpleCart_items" >
		             </div>
                            <div>
                                <!--Here's the Links to Checkout and Empty Cart-->
				<button class="simpleCart_empty btn btn-small">empty cart</button>
				<button class="simpleCart_checkout btn btn-small">Checkout</button>
                            </div>
                         </div>
                        </div>
                        
                    
                    </div>
                </div>
    </div> 
    </body>
</html>
