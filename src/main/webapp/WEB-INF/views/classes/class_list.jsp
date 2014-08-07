<%-- 
    Document   : product_list
    Created on : Jul 30, 2014, 2:14:09 PM
    Author     : eTunkara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table class="table table-striped">    
                     <td>Name</td>
                     <td>Description</td>
                     <td>Tution Fee</td>
                <c:forEach items="${classes}" var="classs">
                 <tr>      
                     <td>${classs.className}</td>
                     <td>${classs.description}</td>
                     <td>${classs.fee}</td>
                 </tr>
               </c:forEach>
</table>
