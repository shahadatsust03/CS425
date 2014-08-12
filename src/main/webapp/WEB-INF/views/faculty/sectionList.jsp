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

<script type="text/javascript">
$.fn.doSaveSection =  function(id) {  
              console.log("do save");
               var radioboxes = document.getElementsByName("radio_id");
                         var value = "";
                         // loop over them all
                         var j = 0;
                         for (var i=0; i<radioboxes.length; i++) {
                            // And stick the checked ones onto an array...
                            if (radioboxes[i].checked) {

                                if(j == 0)
                                 value += radioboxes[i].value ;

                                else
                                    value += "," + radioboxes[i].value;
                                j++;

                            }
                         }               
                            
                  $.ajax({
                    type: "GET",
                                         
                   url: "assignFaculty/" + id + "/"+value, 
                    })
                    .done(function( data ) {
                        window.location.href = "faculty";
                    });              
                  
             
            
            //
            document.getElementById('sectionList').style.display='none';
            document.getElementById('fade').style.display='none';
          }
                </script>>