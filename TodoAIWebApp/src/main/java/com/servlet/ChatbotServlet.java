package com.servlet;

import com.service.GeminiService;
import com.dao.TaskDAO;
import com.model.Task;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ChatbotServlet extends HttpServlet {
    private GeminiService geminiService;
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        geminiService = new GeminiService();
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userInput = request.getParameter("task"); // Changed from userInput to task
        
        if (userInput != null && !userInput.trim().isEmpty()) {
            try {
                // Get AI response
                String aiResponse = geminiService.getSuggestedTask(userInput);
                request.setAttribute("aiResponse", aiResponse);
                
                // Get updated task list
                List<Task> tasks = taskDAO.getTasksByUserId(user.getId());
                request.setAttribute("tasks", tasks);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error processing request: " + e.getMessage());
            }
        }
        
        request.getRequestDispatcher("todolist.jsp").forward(request, response);
    }
}