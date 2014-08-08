<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div>
        <table class="table table-striped">    
                     <td>Name</td>
                     <td>Description</td>
                     <td>Price</td>
                <c:forEach items="${products}" var="product">
                 <tr>      
                     <td><a href="products/${product.id}" >${product.name}</a></td>
                     <td>${product.description}</td>
                     <td>${product.price}</td>
                 </tr>
               </c:forEach>
        </table>
    </div>
</div>
