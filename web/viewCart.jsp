<%-- 
    Document   : viewCart
    Created on : Jun 19, 2023, 7:59:51 AM
    Author     : ASUS
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="anhtht.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Java Book Store</h1>
        
        <c:if test="${not empty sessionScope}">                        
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="item" value="${cart.items}"/>
                <c:if test="${not empty item}">
                    <h1>Your cart include</h1>
                    
                    <form action="removeItemFromCartController" method="POST">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>                        
                                <c:forEach var="dto" items="${item}" varStatus="counter">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${dto.key}
                                        </td>
                                        <td>
                                            ${dto.value}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" value="${dto.key}" />
                                        </td>
                                    </tr>

                                </c:forEach>                        
                                    <tr>
                                        <td colspan="3">
                                            <a href="shopping.html">Add More Items to your Cart</a>
                                        </td>
                                        <td>
                                            
                                            <input type="submit" value="Remove Select Items" name="btAction" />
                                        </td>
                                    </tr>
                            </tbody>
                        </table>
                    </form>
                </c:if>
                    
                <c:if test="${empty item}">
                    <h1>Not item inside cart</h1>
                </c:if><%--check out exist items--%>
            </c:if>
            
            
        </c:if><%--check exist session--%>
        <c:if test="${empty sessionScope}">
            <h2>Not session</h2>
        </c:if><%--check not exist session%>
        
<%--        <%
            //1. Cust goes his/her cart place
            //session là 1 .. obj
            if (session != null) {
                //2. Cust takes his/her cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust get items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        %>
                        <h2>Your cart includes</h2>
                        <form action="DispatcherServlet">
                            <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 0;
                                    for(String key: items.keySet()){
                                        %>
                                        <tr>
                                            <td>
                                                <%= ++count %>
                                            </td>
                                            <td>
                                                <%= key %>
                                            </td>
                                            <td>
<!--                                                get value-->
                                                <%= items.get(key) %>
                                            </td>
                                            <td>
                                                <input type="checkbox" name="chkItem" 
                                                       value="<%= key %>" />
                                            </td>
                                        </tr>
                                        <%                                                                                                                        
                                    }//end traverse items
                                    %>        
                                        <tr>
                                            <!--ngoài form-->
                                            <td colspan="3">
                                                <a href="shopping.html">Add More Items to your Cart</a>
                                            </td>
                                            <td>
                                                <input type="submit" value="Remove Select Items" name="btAction" />
                                            </td>
                                        </tr>
                                    <%
                                %>
                            </tbody>
                        </table>
                        </form>
                        

        <%              return;
                        //4. Cust traverses add item to show
                    }//end items have existed                                            
                }//end cart has existed
                    else{%>Not item into cart<%}
            }//end session has existed
        %>--%>
    </body>
</html>
