<%-- 
    Document   : product_list
    Created on : Jul 30, 2014, 2:14:09 PM
    Author     : Shahadat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table class="table table-striped">    
                     <td>Name</td>
                     <td>Description</td>
                     
                <c:forEach items="${classes}" var="classs">
                 <tr>      
                     <td><a href="classes/${classs.id}"/>${classs.className}</td>
                     <td>${classs.description}</td>
                     
                 </tr>
               </c:forEach>
</table>
