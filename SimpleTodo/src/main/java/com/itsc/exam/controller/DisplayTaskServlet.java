package com.itsc.exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itsc.exam.dao.DBConnectionManager;
import com.itsc.exam.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DisplayTaskServlet {
	
	@Autowired
    private DBConnectionManager dbManager;

    @GetMapping("/displayTasks")
    public void displayTasks(HttpServletResponse response) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbManager.openConnection();
            String sql = "SELECT * FROM Tasks";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status"));
                task.setDueDate(rs.getDate("due_date"));
                tasks.add(task);
            }

            // Generate HTML response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>All Tasks</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Description</th><th>Status</th><th>Due Date</th></tr>");

            for (Task task : tasks) {
                out.println("<tr>");
                out.println("<td>" + task.getId() + "</td>");
                out.println("<td>" + task.getDescription() + "</td>");
                out.println("<td>" + task.getStatus() + "</td>");
                out.println("<td>" + task.getDueDate() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='index.html'>Back to Home</a>");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle error response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3>Error retrieving tasks.</h3>");
            out.println("<br><a href='index.html'>Back to Home</a>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) dbManager.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}