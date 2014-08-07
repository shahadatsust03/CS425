<%-- 
    Document   : addProduct
    Created on : Aug 6, 2014, 4:36:16 PM
    Author     : eTunkara
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
                             <label for="price">Name:</label>
                             <input typ="text" class="form-control" id="price" name="name" value=''/>
                           </div>
                           <div class="form-group">
                             <label for="price">Fee:</label>
                             <input type="text" class="form-control" id="price" placeholder="Price"  name='price'/>
                           </div>
                            <div class="form-group">
                             <label for="description">Description</label>
                             <textarea class="form-control" id="description" placeholder="description"  name='description'></textarea>
                           </div>

                           <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
                           <button type="submit" class="btn btn-primary">Cancel</button>
                           <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
    </div> 
    </body>
</html>
