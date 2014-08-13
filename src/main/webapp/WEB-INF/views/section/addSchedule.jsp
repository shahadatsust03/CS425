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
            
                <div class="container" style="width:60%;height:20%">
                      <%@include file="section_navigation.jsp" %>
                    <div class="form-group"></div>
                          <form role="form" method="post" action="saveSchedule">                               
                           <div class="form-group">
                             <label for="DayOfWeek">Day Of Week: </label>
                             <input type="text" class="form-control" id="DayOfWeek" placeholder="Day of week"  name="DayOfWeek"/>
                           </div>
                           
                           <div class="form-group">
                             <label for="startTime">Start time: </label>
                             <input type="time" class="form-control" id="startTime" placeholder="Start time"  name="startTime"/>
                           </div>
                           
                           <div class="form-group">
                             <label for="endTime">End time: </label>
                             <input type="time" class="form-control" id="endTime" placeholder="End time"  name="endTime"/>
                           </div>                              
                           <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
                           <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>

            <script language="JavaScript">


         
                </script>
    </div> 
    </body>
</html>
