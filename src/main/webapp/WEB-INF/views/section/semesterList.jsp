<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="semesterList" class="white_content">
    <div>
        
       <table class="table table-striped">
           <p>Class List</p>
           <td>Select</td>
                     <td>Name</td>
                     <td>Start date</td>
                     <td>End date</td>
               <tr>      
                     <div>
                      <c:forEach items="${semesters}" var="semester">
                        <tr>      
                            <div>
                            <td><input type="radio" name="semester_id" value="${semester.id}">

                                <td>${semester.semesterName}</td>
                            <td>${semester.getstartDate()}</td>
                            <td>${semester.getendDate()}</td>          

                            </div>

                        </tr>
                      </c:forEach>   
                     
                     </div>
                     
                 </tr>
                 
               </div>
        </table>
  <input type="button" value="Save" onclick="doSaveSemester();document.getElementById('semesterList').style.display='none';document.getElementById('fade').style.display='none'"/>
  <input type="button" value="Cancel" onclick="document.getElementById('semesterList').style.display='none';document.getElementById('fade').style.display='none'"/>
      
               
    </div>
 
</div>
