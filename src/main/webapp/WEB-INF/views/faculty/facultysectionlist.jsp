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
                ${message}
                    <div class="row">
                            <div class="col-sm-8 text-center feature">
                                
                                <h3>My Section List </h3>
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                  <li class="active">
                                      <a href="#currentsemester" role="tab" data-toggle="tab">
                                          <h4>Current Semester</h4>
                                      </a>
                                  </li>
                                   <li>
                                    <a href="#nextsemester" role="tab" data-toggle="tab">
                                        <h4>Next Semester</h4>
                                     </a>
                                   </li>      
                                </ul>

                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div class="tab-pane active" id="currentsemester" style="padding:20px;text-align: left;">
                                        <table class="table table-striped">    
						     <td>Section Name</td>
						     <td>Location</td>
						     <td>Schedule</td>
						<c:forEach items="${sectionlist}" var="section">
						 <tr>      
						     <td>${section.sectionName}</td>
						     <td>${section.location}</td>
						     <td>${section.scheduleText}</td>
						 </tr>
					       </c:forEach>
					</table>
                                    </div>
                                    <div class="tab-pane" id="nextsemester" style="padding:20px;text-align: left;">
                                        <table class="table table-striped">    
						     <td>Section Name</td>
						     <td>Location</td>
						     <td>Schedule</td>
						<c:forEach items="${nextsectionlist}" var="section2">
						 <tr>      
						     <td>${section2.sectionName}</td>
						     <td>${section2.location}</td>
						     <td>${section2.scheduleText}</td>
						 </tr>
					       </c:forEach>
					</table>
                                    </div>
                                </div>                             

                            </div>
                            <div class="col-sm-4 text-center feature">
				    <h4></h4>
				    <p>
				      
					   <%@include file="../user/userprofile.jsp" %>
		                           <%@include file="navi.jsp" %>
                                    </p>
                            </div>
                    </div>
            </div>
    </div> <!-- /featureWrap -->
    </body>
</html>
