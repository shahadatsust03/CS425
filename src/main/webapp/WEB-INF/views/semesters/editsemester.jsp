<%-- 
    Document   : addProduct
    Created on : Aug 6, 2014, 4:36:16 PM
    Author     : Shahadat
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<sec:authentication var="user" property="principal" />
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../header.jsp" %>        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script>
            $(function() {
                $("#startDate").datepicker({dateFormat: "yy-mm-dd"});
            });
        </script>
        <script>
            $(function() {
                $("#endDate").datepicker({dateFormat: "yy-mm-dd"});
            });
        </script>

    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
        <div id="featureWrap">
            <div class="container">
                <form role="form" method="post" action="${pageContext.request.contextPath}/updatesemester">
                    <div class="form-group">
                        <label for="name">Name:</label>                         
                        <input type="text" class="form-control" id="semesterName" name="semesterName" placeholder = "${semester.semesterName}" value="${semester.semesterName}"/>
                    </div>

                    <div class="form-group">
                        <label for="startDate">Start Date :</label>
                        <fmt:parseDate var="parsedStartDate" type="date" value="${semester.startDate}" pattern="yyyy-MM-dd"/>
                        <fmt:formatDate var="formatedStartDate" type="date" value="${parsedStartDate}" pattern="yyyy-MM-dd"/>                                      
                        <input type="text" class="form-control" id="startDate" name="startDate" placeholder="${formatedStartDate}"  value="${formatedStartDate}"/>
                    </div>
                    <div class="form-group">
                        <label for="endDate">End Date : </label>
                        <fmt:parseDate var="parsedEndDate" type="date" value="${semester.endDate}" pattern="yyy-MM-dd"/>
                        <fmt:formatDate var="formatedEndDate" type="date" value="${parsedEndDate}" pattern="yyyy-MM-dd"/> 
                        <input type="text" class="form-control" id="endDate" name="endDate" placeholder="${formatedEndDate}"  value="${formatedEndDate}"/>
                    </div>                           
                    <input type="hidden" name="id" value="${semester.id}" />

                    <a href="${pageContext.request.contextPath}/semesters" class="btn btn-primary">Cancel</a>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form>
            </div>
            <script language="JavaScript">
                function openpopup(anchor) {
                    var popurl = "classes/classPopup" + "#" + anchor;
                    popup_window = window.open(popurl, "", "width=600,height=400,");
                    //popup_window.close();

                }
                function updateValue(value)
                {    // this gets called from the popup window and updates the field with a new value
                    var elem = document.getElementById("prerequestie");
                    elem.value = value;
                }

            </script>
        </div> 
    </body>
</html>
