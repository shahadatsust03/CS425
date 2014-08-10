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
                     <td>Specilization</td>
                     <td>Years of experience</td>
                     <td>Email</td>                  
                   
                <c:forEach items="${facultys}" var="faculty">
                 <tr>                           
                     <td><a href="/faculty/getFaculty/${faculty.id}"/>${faculty.fullname}</td>
                      <td>${faculty.getSpecialization()}</td>
                     <td>${faculty.getYearsOfExperience()}</td>
                     <td>${faculty.email}</td>                     
                    
                </tr>
               </c:forEach>
        </table>
    </div>
</div>
