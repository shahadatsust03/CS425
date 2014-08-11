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
        <div>
                <section  class="container main" style="margin-top: 10px">  
                <ul  class="list-group">
                    <c:forEach items="${orders}" var="order">
                         <li  class="list-group-item"> 
                            <div>
                                <div>
                                    <h5 class="list-group-item-heading" style="font-weight:bold;">
                                          <i class="glyphicon glyphicon-star" style="color:orange   "></i>
                                          <span> ${order.getDateOfOrder().toString()}</span>
                                    </h5>                                                   
                                </div>
                                <table class="table table-striped" >
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                            <th>Amount</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${order.getProductOrders()}" var="product">
                                      <tr>
                                        <td style="width:20%">
                                            <span><a href="${pageContext.request.contextPath}/products/${product.getProduct().getId()}" >${product.getProduct().getName()}</a></span> 
                                        </td>
                                        <td style="width:20%">
                                            <span>${product.getQuantity()}</span> 
                                        </td>
                                        <td style="width:20%">
                                            <span>$ ${product.getTotalAmount()}</span>
                                        </td>
                                        <td style="width:20%">
                                            <span>$ ${product.getPrice()}</span>
                                        </td>
                                       </tr> 
                                    </c:forEach>
                                      <tr>
                                          <td ></td>
                                          <td><span class="badge">${order.totalQuantity}</span></td>
                                          <td></td>
                                          <td >
                                              <span class="badge">
                                                 $ ${order.totalAmount}</span>
                                          </td>
                                      </tr>
                               </table>
                            </div>
                       </li>
                    </c:forEach>            
                </ul>

            </section>
        </div>
    </body>
</html>
