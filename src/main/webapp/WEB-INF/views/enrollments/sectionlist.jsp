<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div>
       <table class="table table-striped">    
                     <td>Section Name</td>
                     <td>Description</td>                     
                      <td>Location</td>
                     <td>Class Limit</td>                      
                <c:forEach items="${sections}" var="section">
                 <tr>      
                     <td><a href="${pageContext.request.contextPath}/enrollmentsections/${section.id}"/>${section.sectionName}</td>
                     <td>${section.description}</td>
                     <td>${section.location}</td>
                      <td>${section.classLimit}</td>
                 </tr>
               </c:forEach>
        </table>
    </div>
</div>
