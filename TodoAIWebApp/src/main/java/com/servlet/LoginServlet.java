package com.servlet;

import com.model.Task;
import com.model.User;
import com.service.UserService;  // Fix the import
import com.dao.TaskDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private UserService userService;
    private TaskDAO taskDAO;

    @Override
    public void init() {
        userService = new UserService();
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.validateLogin(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            // Get user's tasks
            List<Task> tasks = taskDAO.getTasksByUserId(user.getId());
            request.setAttribute("tasks", tasks);
            
            // Forward to todolist.jsp
            request.getRequestDispatcher("todolist.jsp").forward(request, response);
        } else {
            // Add error message and redirect back to login
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect("todolist.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}