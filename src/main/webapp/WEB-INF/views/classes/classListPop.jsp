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
                     <td>Description</td>
                     <td>Tution Fee</td>
                <c:forEach items="${classes}" var="classes">
                 <tr>      
                     <div>
                     <td><input type="checkbox" name="id" value="${classes.id}">
                         
                         <td>${classes.className}</td>
                     <td>${classes.description}</td>
                     <td>${classes.fee}</td>          
                     
                     </div>
                     
                 </tr>
               </c:forEach>
                 
               </div>
        </table>
 <input type="button" value="Submit" onclick="doSave();"/>
                 </form>
    </div>
    <script language="JavaScript1.1">
            function doSave( ) {
                
               var formId = window.openerFormId;
               var checkboxes = document.getElementsByName("id");
                var value = "";
                // loop over them all
                var j = 0;
                for (var i=0; i<checkboxes.length; i++) {
                   // And stick the checked ones onto an array...
                   if (checkboxes[i].checked) {
                       
                       if(j == 0)
                        value += checkboxes[i].value ;
                        
                       else
                           value += "," + checkboxes[i].value;
                       j++;
                  
                   }
                }
                window.opener.updateValue(value);
                window.close();
            }               
        </script>
 
</div>
