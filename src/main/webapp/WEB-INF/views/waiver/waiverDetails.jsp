<%-- 
    Document   : waiverDetails
    Created on : Aug 11, 2014, 4:23:25 PM
    Author     : demodem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../header.jsp" %>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script type="text/javascript" language="JavaScript">
            function validate()
            {   //alert("Entered function.");
                var vComments = document.getElementById('comments');                
                if (vComments.value=='')
                {
                    alert("Provide Comments.");
                    //document.frm.comments.focus();
                    return false;
                }
                //document.frm.submit();
               // return true;
            }
            
            function trim(s) {
                    return s.replace(/^s*/, "").replace(/s*$/, "");
                    alert("String:"+s);
                            }

        </script> 
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
        <div id="featureWrap">   
            <c:if test="${empty waiver}">
                User Not Found.
            </c:if>
            <c:if test="${not empty waiver}">
                <div class="container">
                    <c:if test="${not empty message}">
                        <b> ${message}</b>
                    </c:if>
                    <div class="row">
                        <div class="col-sm-8 text-center feature">
                            <h3 align="left">Waiver Details:</h3>
                            <form onsubmit="return validate()" action="../waiver/respondOnWaiver/${waiver.id}" method="post">
                                <table class="table table-striped" align="center">    
                                    <td>Class</td>
                                    <td>Reason</td>
                                    <td>Submitted Date</td>
                                    <td>Faculty Advisor</td>
                                    <td>Comments</td>
                                    <td>Status</td>
                                    <td>Updated Date</td>                                       

                                    <tr>      
                                        <td>${waiver.yogaClass.className}</td>
                                        <td>${waiver.reason}</td>
                                        <td>${waiver.submissionDate}</td>
                                        <td>${waiver.faculty.fullname}</td>
                                        <td>${waiver.comments}</td>
                                        <td>${waiver.status}</td>
                                        <td>${waiver.updateDate}</td>                                                                                                                                                         
                                    </tr>

                                </table>

                                <div class="form-group" align="left">
                                    <label for="reason" align="left">Enter Comments:</label>
                                    <input type="text" class="form-control" id="comments" placeholder="Comments"  name="comments"/>
                                </div>
                                <div align="center"><input name="response" type="submit"  value="Approve Waiver">  
                                    <input name="response" type="submit"  value="Reject Waiver"></div>               
                            </form>
                        </c:if>
                    </div>
                    <div class="col-sm-4 text-center feature">
                        <h4></h4>
                        <p>
                            <%@include file="../user/userprofile.jsp" %>
                            <%@include file="../faculty/navi.jsp" %>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
