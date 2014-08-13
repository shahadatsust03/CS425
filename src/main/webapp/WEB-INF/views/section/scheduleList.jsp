<%-- 
    Document   : main_product_list
    Created on : Aug 6, 2014, 5:35:15 PM
    Author     : Shahadat
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="scheduleList" class="white_content">
    <div>
        
       <table class="table table-striped">
           <p>Schedule List</p>
           <td>Select</td>
                     <td>Day of week</td>
                     <td>Start date</td>
                     <td>End date</td>
               <tr>      
                     <div>
                      <c:forEach items="${schedules}" var="schedule">
                        <tr>      
                            <div>
                            <td><input type="checkbox" name="schedule_id" value="${schedule.id} classname="${classs.className}"">

                            <td>${schedule.dayOfWeek}</td>
                            <td>${schedule.getStartTime()}</td>
                            <td>${schedule.getEndTime()}</td>          

                            </div>

                        </tr>
                      </c:forEach>   
                     
                     </div>
                     
                 </tr>
                 
               </div>
        </table>
  <input type="button" value="Save" onclick="doSaveSchedule();document.getElementById('scheduleList').style.display='none';document.getElementById('fade').style.display='none'"/>
    <input type="button" value="Cancel" onclick="document.getElementById('scheduleList').style.display='none';document.getElementById('fade').style.display='none'"/>
              
    </div>
 
</div>
