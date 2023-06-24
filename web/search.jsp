<%-- 
    Document   : search
    Created on : Jun 1, 2023, 1:31:00 PM
    Author     : ASUS
--%>

<%@page import="anhtht.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font style="color: red">
        Welcome, ${sessionScope.USER.lastname}
        </font>
        <h1 style="color: blue">Search Page</h1>
        
        <form action="DispatcherServlet">
            Search <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"><br/>
            <input type="submit" value="Search" name="btAction"/>
            <input type="submit" value="Logout" name="btAction" />
        </form>
        
        <%--<%
//            Login thành công khi cookie tồn tại
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                   Cookie newestCookie = cookies[cookies.length - 1];
                   String username = newestCookie.getName();                                          
                   %>
                   <font style="color: red">
                   Welcome, <%= username %>
                    </font>
        <%          
                }
        %>
        
        
        <form action="DispatcherServlet">
            Search <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue") %>"><br/>
            <input type="submit" value="Search" name="btAction"/>
            <input type="submit" value="Logout" name="btAction" />
        </form>
        
        <% 
            String searchValue = request.getParameter("txtSearchValue");
            
            if (searchValue != null) {
                // tại sao obj phải ép kiểu
                // do obj seriable 
                List<RegistrationDTO> result = (List<RegistrationDTO>)
                        request.getAttribute("SEARCH_RESULT");
                //lấy xong fai check
                if (result != null) {
                    %>
                    
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>FullName</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for(RegistrationDTO dto: result){
                                    String urlRewriting = "DispatcherServlet"
                                            + "?btAction=Delete"
                                            + "&pk=" + dto.getUsername()
                                            + "&LastSearchValue=" + searchValue;
                                    //req para còn exist do config para
                                    %>
                                    
                        <form action="DispatcherServlet" method="POST">
                            <tr>
                                        <td>
                                            <%= ++count%>
                                        .</td>
                                        <td>
                                            <%= dto.getUsername() %>
                                            <input type="hidden" name="txtUsername" 
                                                   value="<%= dto.getUsername() %>" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtPassword" 
                                                   value="<%= dto.getPassword() %>"/>
                                        </td>
                                        <td>
                                            <%= dto.getLastname() %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkAdmin" value="ON" 
                                                   <% 
                                                    if (dto.isRole()) {
                                                      %>
                                                      checked="checked"
                                                   <%
                                                    } //end user is an admin
                                                   %>
                                                   />
                                        </td>                                        
                                        <td>
                                            <a href="<%= urlRewriting %>">Delete</a>
                                        </td>
                                        <td>
                                            <input type="hidden" name="lastSearchValue" 
                                                   value="<%= searchValue %>" />
                                            <input type="submit" value="Update" name="btAction" />
                                        </td>
                                    </tr>
                        </form>
                                    <%
                                }// end get data from result
                            %>
                        </tbody>
                    </table>

            <%
                } else { //result == null
                    %>
            
                    <h2>
                        No record is matched!!!
                    </h2>
                    
            <%
                }
            }// end page is called invalid
            
            
        %>--%>
    </body>
</html>
