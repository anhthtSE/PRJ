<%-- 
    Document   : createNewAccount
    Created on : Jun 26, 2023, 8:59:12 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create new account </h1>
        <form action="DispatcherServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="" /> (6 - 20 chars)<br/>
            Password <input type="password" name="txtPassword" value="" /> (6 - 30 chars)<br/>
            Confirm <input type="password" name="txtConfirm" value="" />
            FullName <input type="text" name="txtFullName" value="" /> (2 - 50 chars)<br/>
            <input type="submit" value="Create New Account" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
