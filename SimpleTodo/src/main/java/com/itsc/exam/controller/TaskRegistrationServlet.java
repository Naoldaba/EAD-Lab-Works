package com.itsc.exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itsc.exam.dao.DBConnectionManager;

@Controller
public class TaskRegistrationServlet {
	
	@Autowired
    private DBConnectionManager dbManager;

    @PostMapping("/registerTask")
    public void registerTask(@RequestParam("description") String description,
                             @RequestParam("status") String status,
                             @RequestParam("due_date") String dueDateStr,
                             HttpServletResponse response) throws IOException {
        java.sql.Date dueDate = null;
        if (dueDateStr != null && !dueDateStr.isEmpty()) {
            try {
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateStr);
                dueDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbManager.openConnection();
            String sql = "INSERT INTO Tasks (description, status, due_date) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, description);
            pstmt.setString(2, status);
            pstmt.setDate(3, dueDate);
            int rowsInserted = pstmt.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (rowsInserted > 0) {
                out.println("<h3>Task registered successfully!</h3>");
            } else {
                out.println("<h3>Failed to register task.</h3>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) dbManager.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}