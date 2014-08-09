<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div>
         <form role="form">
          
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
                            <td><input type="radio" name="id" value="${semester.id}">

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
 <input type="button" value="Submit" onclick="doSave();"/>
                 </form>
    </div>
    <script language="JavaScript1.1">
            function doSave( ) {
                
               var formId = window.openerFormId;
               var radioboxes = document.getElementsByName("id");
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
                window.opener.updateValueSemester(value);
                window.close();
            }               
        </script>
 
</div>
