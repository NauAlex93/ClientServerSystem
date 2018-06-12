package ru.specialist.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.specialist.dao.UserDAOLocal;
import ru.specialist.entity.User;


@WebServlet(name = "UserFindByCityServlet", urlPatterns = {"/UserFindByCityServlet"})
public class UserFindByCityServlet extends HttpServlet {
        
    @EJB
    private UserDAOLocal userDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String city = request.getParameter("city");
        if (city == null) {
            city = "";
        }
        
        List<User> users = new ArrayList<>();
        users.addAll(userDAO.findByCity(city, 0, 100));
        
        request.setAttribute("users", users);        
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/find.jsp").forward(request, response);
    }

}
