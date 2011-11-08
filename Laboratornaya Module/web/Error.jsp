<%-- 
    Document   : Error
    Created on : Sep 30, 2011, 4:30:58 PM
    Author     : sikorskyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="EmpInfoSubmit">
        <h1>${ErrorMessage}</h1>
        <input  type="submit" name="addEmp" value="Ok" onclick="EmpInfoSubmit">
    </body>
</html>
