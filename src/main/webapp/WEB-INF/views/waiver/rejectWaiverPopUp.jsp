<%-- 
    Document   : approveWaiverPopUp
    Created on : Aug 11, 2014, 3:05:45 PM
    Author     : demodem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <form action="../../waiver/rejectWaiver/${waiver.id}" method="post">
                    <h1>Enter Comments:</h1>
                    <div class="form-group">
                        <label for="reason">Comments:</label>
                        <input type="text" class="form-control" id="comments" placeholder="Comments"  name="comments"/>
                    </div>
                    <div><br/><input type="submit" value="OK"></div></form>
            </div>
        </div>
    </body>
</html>
