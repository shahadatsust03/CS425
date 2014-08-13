<%-- 
    Document   : addProduct
    Created on : Aug 6, 2014, 4:36:16 PM
    Author     : Shahadat
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <sec:authentication var="user" property="principal" />
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
                 <style>
    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=80);
    }
    .white_content {
        display: none;
        position: absolute;
        top: 25%;
        left: 25%;
        width: 50%;
        height: 50%;
        padding: 16px;
        border: 2px solid orange;
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
</style>
        
        <%@include file="../header.jsp" %>
         <%@include file="classPop.jsp" %>
         <%@include file="semesterPop.jsp" %>
          <%@include file="schedulePop.jsp" %>
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
            <div id="featureWrap">
            
                <div class="container">
                     <c:forEach items="${message}" var="msg">
                        <td>${msg}</td>
                    </c:forEach>
                          <form role="form" method="post" action="save">
                               
                           <div class="form-group">
                             <label for="name">Name:</label>
                             <input type="text" class="form-control" id="name" name="name" placeholder = "Name" value="${section.sectionName}"/>
                           </div>
                           <div class="form-group">
                             <label for="descripton">Description:</label>
                             <input type="text" class="form-control" id="descripton" placeholder="Descripton"  name="descripton" value="${section.descripton}"/>
                           </div>
                          
                           <div class="form-group">
                             <label for="classToAssign">Class: </label>
                             <textarea readonly class="form-control" id="classToAssign" placeholder="Class To Assign"  name="classToAssign" value = "${section.classEntity.className}"></textarea>
                             <a href = "javascript:void(0)" onclick = "document.getElementById('classList').style.display='block';document.getElementById('fade').style.display='block'">Add Class</a>
                           </div>
                          
                           <div class="form-group">
                           <label for="semesterToAssign">Semester </label>
                             <textarea readonly class="form-control" id="semesterToAssign" placeholder="Semester To Assign"  name="semesterToAssign" value = "${section.semester.semesterName}"></textarea>
                             <a href = "javascript:void(0)" onclick = "document.getElementById('semesterList').style.display='block';document.getElementById('fade').style.display='block'">Add semester</a>
                           </div>
                                
                           <div class="form-group">
                             <label for="location">Location:</label>
                             <input type="text" class="form-control" id="location" placeholder="Location"  name="location" value = "${section.location}"/>
                           </div>
                            <div class="form-group">
                             <label for="classLimit">Class Limit </label>
                             <input type="text" class="form-control" id="classLimit" placeholder="Class Limit"  name="classLimit" value = "${section.classLimit}"/>
                           </div>
                            <div class="form-group">
                             <label for="schedules">Schedule List: </label>
                             <input readonly type="text" class="form-control" id="schedules" placeholder="Schedule List"  name="schedules" value = "${section.getSchedules()}"/>
                             <a href = "javascript:void(0)" onclick = "document.getElementById('scheduleList').style.display='block';document.getElementById('fade').style.display='block'">Add Schedules</a>
                            </div>
   
                           <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
                           <input type="hidden" name="idsPrereq" value="">
                           <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>

            <script language="JavaScript">


                function doSaveClass( ) {   

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

                    document.getElementById('classToAssign').value = value;                

                }               
       
                function doSaveSemester( ) {
                        var radioboxes = document.getElementsByName("semester_id");
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
                         document.getElementById('semesterToAssign').value = value;  
                     }               
                     
                     function doSaveSchedule( ) {
                        var radioboxes = document.getElementsByName("schedule_id");
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
                         document.getElementById('schedules').value = value;  
                     }               
                </script>
    </div> 
    </body>
</html>
