<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div>
       <table class="table table-striped">    
                     <td>Semester Name</td>
                     <td>Start Date</td>
                     <td>End Date</td>
                <c:forEach items="${semesters}" var="semester">
                 <tr>      
                     <td><a href="${pageContext.request.contextPath}/semesters/${semester.id}"/>${semester.semesterName}</td>
                     <td>${semester.startDate}</td>
                     <td>${semester.endDate}</td>
                 </tr>
               </c:forEach>
        </table>
    </div>
</div>
