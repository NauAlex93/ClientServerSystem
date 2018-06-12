package ru.specialist.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.specialist.dao.UserDAORemote;
import ru.specialist.entity.Address;

import ru.specialist.entity.User;


@WebServlet(name = "UserAddServlet", urlPatterns = {"/UserAddServlet"})
public class UserAddServlet extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
            
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        
        if (city != null && !city.isEmpty()) {
            Address address = new Address();
            address.setCity(city);
            user.setAddress(address);
        }
        
        try {    
            javax.naming.InitialContext ctx = new javax.naming.InitialContext();
            UserDAORemote userDAO = (UserDAORemote) ctx.lookup("UserDAO");
            userDAO.create(user);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
        this.getServletContext().getRequestDispatcher("/").forward(request, response);
    }

    

}
