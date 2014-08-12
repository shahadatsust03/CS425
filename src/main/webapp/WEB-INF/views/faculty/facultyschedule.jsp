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
                            <div class="col-sm-9 text-center feature">
                                
                                <h3>My Section Schedule </h3>
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
                                            <tr>
                                                <td>Sunday</td>
                                                <td>Monday</td>
                                                <td>Tuesday</td>
                                                <td>Wednesday</td>
                                                <td>Thursday</td>
                                                <td>Friday</td>
                                                <td>Saturday</td>
                                            </tr>
                                            <tr>  
                                                <c:forEach begin="0" end="6" varStatus="loop">
                                                   <c:set var="key" value="${loop.index}AM" />
                                                   <c:set var="schedule" value="${schedulemap[key]}"/>
                                                   <c:set var="keysec" value="${loop.index}AMSEC" />
                                                   <c:set var="section" value="${schedulemap[keysec]}"/>
                                                   <td>
                                                   <c:out value="${schedule.startTimeM}"/> - <c:out value="${schedule.endTimeM}"/><br>
                                                   <c:out value="${section.sectionName}"/><br>
                                                   <c:out value="${section.location}"/>
                                                   </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>      
                                                <c:forEach begin="0" end="6" varStatus="loop">
                                                   <c:set var="key" value="${loop.index}PM" />
                                                   <c:set var="schedule" value="${schedulemap[key]}"/>
                                                   <c:set var="keysec" value="${loop.index}PMSEC" />
                                                   <c:set var="section" value="${schedulemap[keysec]}"/>
                                                   <td>
                                                   <c:out value="${schedule.startTimeM}"/> - <c:out value="${schedule.endTimeM}"/><br>
                                                   <c:out value="${section.sectionName}"/><br>
                                                   <c:out value="${section.location}"/>
                                                   </td>
                                                </c:forEach>
                                            </tr>               
					</table>

                                    </div>
                                    <div class="tab-pane" id="nextsemester" style="padding:20px;text-align: left;">
				       <table class="table table-striped">  
                                            <tr>
                                                <td>Sunday</td>
                                                <td>Monday</td>
                                                <td>Tuesday</td>
                                                <td>Wednesday</td>
                                                <td>Thursday</td>
                                                <td>Friday</td>
                                                <td>Saturday</td>
                                            </tr>
                                            <tr>  
                                                <c:forEach begin="0" end="6" varStatus="loop">
                                                   <c:set var="key" value="${loop.index}AM" />
                                                   <c:set var="schedule" value="${schedulemap2[key]}"/>
                                                   <c:set var="keysec" value="${loop.index}AMSEC" />
                                                   <c:set var="section" value="${schedulemap2[keysec]}"/>
                                                   <td>
                                                   <c:out value="${schedule.startTimeM}"/> - <c:out value="${schedule.endTimeM}"/><br>
                                                   <c:out value="${section.sectionName}"/><br>
                                                   <c:out value="${section.location}"/>
                                                   </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>      
                                                <c:forEach begin="0" end="6" varStatus="loop">
                                                   <c:set var="key" value="${loop.index}PM" />
                                                   <c:set var="schedule" value="${schedulemap2[key]}"/>
                                                   <c:set var="keysec" value="${loop.index}PMSEC" />
                                                   <c:set var="section" value="${schedulemap2[keysec]}"/>
                                                   <td>
                                                   <c:out value="${schedule.startTimeM}"/> - <c:out value="${schedule.endTimeM}"/><br>
                                                   <c:out value="${section.sectionName}"/><br>
                                                   <c:out value="${section.location}"/>
                                                   </td>
                                                </c:forEach>
                                            </tr>               
					</table>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-3 text-center feature">
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
