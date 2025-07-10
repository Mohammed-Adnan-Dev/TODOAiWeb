package com.servlet;

import com.dao.TaskDAO;
import com.model.Task;
import com.model.User;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class TodoServlet extends HttpServlet {
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
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

    String taskContent = request.getParameter("task");
    if (taskContent != null && !taskContent.trim().isEmpty()) {
        try {
            Task task = new Task();
            task.setTask(taskContent);
            task.setUserId(user.getId());
            task.setCreatedDate(new Date()); // Set current date
            taskDAO.addTask(task);
            
            // Get updated task list
            List<Task> tasks = taskDAO.getTasksByUserId(user.getId());
            request.setAttribute("tasks", tasks);
        } catch (Exception e) {
            request.setAttribute("error", "Error adding task: " + e.getMessage());
        }
    }

    request.getRequestDispatcher("todolist.jsp").forward(request, response);
}
}