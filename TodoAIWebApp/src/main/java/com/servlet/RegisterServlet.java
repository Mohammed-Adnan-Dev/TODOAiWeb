package com.servlet;

import com.dao.UserDAO;
import com.model.User;
import com.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;  // Change to UserService

    public void init() {
        userService = new UserService();  // Initialize UserService instead of UserDAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            userService.registerUser(username, password);  // Use UserService method
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            // Add error handling
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}