<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
/*var countChecked = function() {
  var n = $( "input:checked" ).length;
  console.debug(n);
  console.debug("number of i")
};
countChecked();
 
$( "input[type=checkbox]" ).on( "click", countChecked );
*/
</script>
<div>
    <div>
       <table class="table table-striped">    
                     
                     <td>Name</td>
                     <td>Description</td>
                     <td>Class Name</td>
                     <td>Location</td>
                     <td>Class limit</td>
                   
                <c:forEach items="${sections}" var="section">
                 <tr>                           
                     <td><a href="section/${section.id}"/>${section.sectionName}</td>
                     <td>${section.descripton}</td>
                     <td>${section.classEntity.className}</td>
                     <td>${section.location}</td>
                     <td>${section.classLimit}</td>                     
                    
                </tr>
               </c:forEach>
        </table>
    </div>
</div>
