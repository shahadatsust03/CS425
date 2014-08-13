<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
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
        
   <c:if test="${empty orders}">  
       <li  class="list-group-item">
           <div class="panel-body" style="min-height: 300px">
             
                <div class="alert alert-info">

                    <strong>Make orders!</strong>  There are no current orders

                </div>
              
           </div>
       </li>
   </c:if>
</ul>

