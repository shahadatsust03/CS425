<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : eTunkara
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="container" style="padding: 20px 0;background: #f5f5f5;width:auto;">    
        <ul class="gallery col-4">
            <c:forEach items="${products}" var="product">
                 <li>
                     <a href="products/${product.id}" style="text-decoration: none;">
                    <div class="preview">
                        <img src="${pageContext.request.contextPath}/products/image/${product.getFirstImage().getId()}" alt="${product.name}" style="width:200px;" />
                    </div>
                    </a>
                    <div class="desc">
                        <h5 style="font-weight: bold">${product.name}</h5>
                         <span>$ ${product.price}</span>               
                                <button 
                                 onclick="simpleCart.add('id=${product.id}','name=${product.name}','price=${product.price}','image=${pageContext.request.contextPath}/products/image/${product.getFirstImage().getId()}');" type="button" class="btn btn-success btn-primary btn-xs">
                                      Add to order
                               </button>

                    </div>  
                   </a>
               </li>
            </c:forEach>            
        </ul>   
    </div>
