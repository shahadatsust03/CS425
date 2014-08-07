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
                    <div class="row">
                            <div class="col-sm-4 text-center feature">
                                    <h4>Lastest Classes</h4>
                                    <p>
                                       <%@include file="../classes/class_list.jsp" %>
                                    </p>
                            </div>
                            <div class="col-sm-4 text-center feature">
                                    <h4>Lastest Products</h4>
                                    <p>
                                       <%@include file="../product/product_list.jsp" %>
                                    </p>
                            </div>
                            <div class="col-sm-4 text-center feature">
                                    <h4></h4>
                                    <p>
                                       <%@include file="view_profile.jsp" %>
                                    </p>
                            </div>
                    </div>
            </div>
    </div> <!-- /featureWrap -->
    </body>
</html>
