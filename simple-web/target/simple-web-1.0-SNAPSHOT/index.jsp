<%@page import="java.util.List"%>
<%@page import="ru.specialist.dao.UserDAORemote"%>
<%@page import="ru.specialist.entity.User"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <a href="UserFindByCityServlet">поиск</a><hr>
        
        <hr>
        <form method="post" action="UserAddServlet">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="city"></td>
                </tr>
                <tr>                    
                    <td colspan="2"><input type="submit"></td>
                </tr>
            </table>
        </form>
        
        <hr>          
        <table border="1">
        <%
            try {
                javax.naming.InitialContext ctx = new javax.naming.InitialContext();
                UserDAORemote userDAO = (UserDAORemote) ctx.lookup("UserDAO");
                List<User> users = userDAO.findAll();
                for(User u : users) {
                    out.print("<tr>");
                    out.print("<td>" + u.getEmail() + "</td>");
                    out.print("<td>" + u.getName() + "</td>");
                    out.print("<td>" + (u.hasAddress() ? u.getAddress().getCity() : "") + "</td>");
                    out.print("</tr>");
                }
            } catch (Exception e) {
                out.println(e);
            }
        %>
        </table>
        
    </body>
</html>