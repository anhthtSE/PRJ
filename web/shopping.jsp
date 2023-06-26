<%-- 
    Document   : shopping
    Created on : Jun 26, 2023, 3:16:39 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Page</title>
    </head>
    <body>
        <h1>Shopping product Page</h1>
        
        <form action="DispatcherServlet">
            <input type="text" name="txtSearchValue" value="" /><br/>
            <input type="submit" value="Search Product" name="btAction" />
        </form>
        <br/>
        
        <c:set var="searchValue" value=""/>
        
    </body>
</html>
