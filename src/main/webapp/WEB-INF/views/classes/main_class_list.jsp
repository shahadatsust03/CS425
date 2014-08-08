<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div>
       <table class="table table-striped">    
                     <td>Name</td>
                     <td>Description</td>
                     <td>Tution Fee</td>
                <c:forEach items="${classes}" var="classes">
                 <tr>      
                     <td><a href="classes/${classs.id}"/>${classes.className}</td>
                     <td>${classes.description}</td>
                     <td>${classes.fee}</td>
                 </tr>
               </c:forEach>
        </table>
    </div>
</div>
