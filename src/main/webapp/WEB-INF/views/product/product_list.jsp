<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div>
        <table class="table table-striped">    
                <c:forEach items="${products}" var="product">
                 <tr>      
                     <td>
                         <div>
                             <a href="products/${product.id}" >${product.name}</a>
                         </div>
                         <div  class="badge">$ ${product.price}</div>
                     </td>
                     <td>
                         <span>
                           <img src="${pageContext.request.contextPath}/products/image/${product.getFirstImage().getId()}" alt="${product.name}" style="width:80px;" />
                         </span>  
                     </td>
                 </tr>
               </c:forEach>
        </table>
    </div>
</div>
