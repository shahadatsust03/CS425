<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sectionList" class="white_content">
    <div>
        
       <table class="table table-striped">
          
           <td>Select</td>
                     <td>Name</td>
                     <td>Class</td>                    
                     <td>Semester</td>                     
               <tr>      
                     <div>
                         
                     <c:if test="${not empty sections}"> <div>Section List: </div> </c:if>
                        <c:forEach items="${sections}" var="section">     
                        <td><input type="radio" name="radio_id" value="${section.id}">

                        <td>${section.sectionName}</td>
                            
                        <c:if test="${not empty section.classEntity}"><td> ${section.classEntity.className}</tc>
                            </c:if>
                        <c:if test="${not empty section.semester}"><td>${section.semester.semesterName}</td>
                            </c:if>
                        </c:forEach>
                     
                     </div>
                     
                 </tr> 
        </table>
             
        <input type="button" value="Save" onclick="$(this).doSaveSection(${faculty.id});"/>
        <input type="button" value="Cancel" onclick="document.getElementById('sectionList').style.display='none';document.getElementById('fade').style.display='none'"/>

    </div>    
 
</div>


<div id="fade" class="black_overlay"></div>