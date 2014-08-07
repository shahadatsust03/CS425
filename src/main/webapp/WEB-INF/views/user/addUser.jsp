<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a User</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form:form commandName="user" action="addUser" method="post">
            <form:errors path="*" cssClass="errorblock" element="div" />
            <table>
                <tr>
                    <td>USERNAME:</td>
                    <td><form:input path="username" /> </td>
                    <td><form:errors path="username" cssClass="error" /> </td>
                </tr>
                <tr>
                    <td>PASSWORD:</td>
                    <td><form:input path="password" /> </td>
                    <td><form:errors path="password" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>FULLNAME:</td>
                    <td><form:input path="fullname" /> </td>
                    <td><form:errors path="fullname" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>EMAIL:</td>
                    <td><form:input path="email" /> </td>
                    <td><form:errors path="email" cssClass="error"/> </td>
                </tr>
                
                <tr>
                    <td>ACTIVATION:</td>
                    <td><form:input path="activests" /> </td>
                    <td><form:errors path="activests" cssClass="error"/> </td>
                </tr>
                
                <tr>
                    <td>ROLE:</td>
                    <td><form:input path="AUTHORITY" /> </td>
                    <td><form:errors path="AUTHORITY" cssClass="error"/> </td>
                </tr>
                
                <tr>
                    <td>RANK:</td>
                    <td><form:input path="userranked" /> </td>
                    <td><form:errors path="userranked" cssClass="error"/> </td>
                </tr>
                
                <tr>
                    <td>USER LABEL:</td>
                    <td><form:input path="userlabel" /> </td>
                    <td><form:errors path="userlabel" cssClass="error"/> </td>
                </tr>
                
                <tr>
                    <td>USER RATING:</td>
                    <td><form:input path="userrating" /> </td>
                    <td><form:errors path="userrating" cssClass="error"/> </td>
                </tr>
                
                
                
            </table>
            <input type="submit"/>

        </form:form>
    </body>
</html>