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
        <%@include file="../header.jsp" %>
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
            <div id="featureWrap">
                <div class="container">
                            <form role="form" method="post" action="save">
                           <div class="form-group">
                             <label for="name">Name:</label>
                             <input type="text" class="form-control" id="name" name="name" placeholder = "Name" value="${section.sectionName}"/>
                           </div>
                           <div class="form-group">
                             <label for="descripton">Description:</label>
                             <input type="text" class="form-control" id="descripton" placeholder="Descripton"  name="descripton" value="${section.description}"/>
                           </div>
                           <div class="form-group">
                             <label for="classToAssign">Class: </label>
                             <textarea class="form-control" id="classToAssign" placeholder="Class To Assign"  name="classToAssign" value = "${section.classEntity.className}" onclick="javascript:openpopup('section/sectionPopup')"></textarea>
                           </div>
                           <div class="form-group">
                             <label for="semesterToAssign">Semester </label>
                             <textarea class="form-control" id="semesterToAssign" placeholder="Semester To Assign"  name="semesterToAssign" value = "${section.sectionEntity.sectionName}" onclick="javascript:openpopup('section/semesterPopup')"></textarea>
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
                             <label for="DayOfWeek">Day Of Week: </label>
                             <input type="text" class="form-control" id="DayOfWeek" placeholder="Day of week"  name="DayOfWeek" value = "${section.dayOfWeek}"/>
                           </div>
                           
                           <div class="form-group">
                             <label for="startTime">Start time: </label>
                             <input type="time" class="form-control" id="startTime" placeholder="Start time"  name="startTime" value = "${section.getStartTime()}"/>
                           </div>
                           
                           <div class="form-group">
                             <label for="endTime">End time: </label>
                             <input type="time" class="form-control" id="endTime" placeholder="End time"  name="endTime" value = "${section.getEndTime()}"/>
                           </div>
   
                           <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
                           <input type="hidden" name="idsPrereq" value="">
                         
                           <button type="submit" class="btn btn-primary">Cancel</button>
                           <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
                           <script language="JavaScript">
function openpopup(anchor){ 
    var popurl="section/sectionPopup"+"#"+anchor;
    popup_window = window.open(popurl,"","width=600,height=400,");

} 
function updateValue(value)
{    // this gets called from the popup window and updates the field with a new value
    var elem = document.getElementById("classToAssign");
    elem.value = value;
}

function updateValueSemester(value)
{    // this gets called from the popup window and updates the field with a new value
    var elem = document.getElementById("semesterToAssign");
    elem.value = value;
}             
                           </script>
    </div> 
    </body>
</html>
