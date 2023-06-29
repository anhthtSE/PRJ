<%-- 
    Document   : createNewAccount
    Created on : Jun 26, 2023, 8:59:12 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create new account </h1>
        <form action="DispatcherServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> (6 - 20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font>
                    ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font>
                    ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font>
                    ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" />
            <c:if test="${not empty errors.confirmNotMatched}">
                <font>
                    ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            FullName* <input type="text" name="txtFullName" 
                            value="${param.txtFullname}" /> (2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font>
                    ${errors.fullNameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
