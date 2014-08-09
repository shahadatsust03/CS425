<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div>
       <table class="table table-striped">    
                     <td>ID</td>
                     <td>Name</td>
                     <td>Description</td>
                     <td>Class Name</td>
                     <td>Location</td>
                     <td>Class limit</td>
                     <td>Day of week</td>
                     <td>Start date</td>
                     <td>End date</td>
                <c:forEach items="${sections}" var="section">
                 <tr>      
                     <td><a href="section/${section.id}"/>${section.id}</td>
                     <td>${section.sectionName}</td>
                     <td>${section.descripton}</td>
                     <td>${section.classEntity.className}</td>
                     <td>${section.location}</td>
                     <td>${section.classLimit}</td>                     
                     <c:forEach items="${section.scheduleList}" var="schedule">                     
                     <td>${schedule.dayOfWeek}</td>
                     <td>${schedule.getStartTime()}</td>
                     <td>${schedule.getEndTime()}</td>
                     </c:forEach>
                </tr>
               </c:forEach>
        </table>
    </div>
</div>
