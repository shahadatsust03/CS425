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
        <script>
            $(document).ready(function(){
                            $('input[type=file]').bootstrapFileInput();
                            $('.file-inputs').bootstrapFileInput();
                
            });
        </script>
    </head>
    <body>
        <%@include file="../nagivation.jsp" %>
            <div id="featureWrap">
                <div class="container">
                     ${message}
                            <form role="form" method="post" action="${pageContext.request.contextPath}/product/save" enctype="multipart/form-data">
                           <div class="form-group">
                             <label for="price">Name:</label>
                             <input typ="text" class="form-control" id="price" name="name" value=''/>
                           </div>
                           <div class="form-group">
                             <label for="price">Price:</label>
                             <input type="text" class="form-control" id="price" placeholder="Price"  name='price'/>
                           </div>
                           <div class="form-group">
                             <label for="numberOfProducts">Number Of Products:</label>
                             <input type="text" class="form-control" id="numberOfProducts" placeholder="Number of Products"  name='numberOfProducts'/>
                           </div>
                            <div class="form-group">
                             <label for="description">Description</label>
                             <textarea class="form-control" id="description" placeholder="description"  name='description'></textarea>
                           </div>
                           <div class="form-group">
                             <input  type="file" placeholder="upload"  name="file" />
                           </div>
                         
                           <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
                           <button type="submit" class="btn btn-primary">Cancel</button>
                           <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
    </div> 
    </body>
</html>
