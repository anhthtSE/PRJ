<%-- 
    Document   : viewCart
    Created on : Jun 19, 2023, 7:59:51 AM
    Author     : ASUS
--%>

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
        <%
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
        %>
    </body>
</html>
