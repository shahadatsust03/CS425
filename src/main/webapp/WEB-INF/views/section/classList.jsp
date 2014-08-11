<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="classList" class="white_content">
    <div>
        
       <table class="table table-striped">
           <p>Class List</p>
           <td>Select</td>
                     <td>Name</td>
                     <td>Description</td>
                     <td>Tution Fee</td>
               <tr>      
                     <div>
                      <c:forEach items="${classes}" var="classs">
                        <tr>      
                            <div>
                                <td><input type="radio" name="radio_id" value="${classs.id}">

                                    <td>${classs.className}</td>
                                <td>${classs.description}</td>
                                <td>${classs.fee}</td>          

                            </div>

                        </tr>
                      </c:forEach>   
                     
                     </div>
                     
                 </tr> 
        </table>
             
        <input type="button" value="Save" onclick="doSaveClass();document.getElementById('classList').style.display='none';document.getElementById('fade').style.display='none'"/>
        <input type="button" value="Cancel" onclick="document.getElementById('classList').style.display='none';document.getElementById('fade').style.display='none'"/>

    </div>    
 
</div>


<div id="fade" class="black_overlay"></div>