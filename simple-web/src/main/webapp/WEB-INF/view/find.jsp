<%@page import="java.util.List"%>
<%@page import="ru.specialist.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.jsp">Home</a><hr>
        <form method="get" action="UserFindByCityServlet">
            <input type="text" name="city">
            <input type="submit">
        </form>
        <hr>
        <table border="1">
            <%for(User u: (List<User>) request.getAttribute("users")){%>
            <tr>
                <td><%=u.getEmail()%></td>
                <td><%=u.getName()%></td>
                <td><%=(u.hasAddress() ? u.getAddress().getCity() : "")%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
