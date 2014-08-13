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
                  <%@include file="products_navigation.jsp" %>
                  <div id="productsList">
                     <%@include file="main_product_list.jsp" %>
                  </div>
            </div>
        </div>
                  <script>
                      $(".dropdown-menu li a").click(function(){
                        var selText = $(this).text();
                        $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
                      });
                  </script>
    </body>
</html>