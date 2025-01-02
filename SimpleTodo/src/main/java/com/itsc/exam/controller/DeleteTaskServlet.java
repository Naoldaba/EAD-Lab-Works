package com.itsc.exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itsc.exam.dao.DBConnectionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DeleteTaskServlet {
	
	@Autowired
    private DBConnectionManager dbManager;

    @PostMapping("/deleteTask")
    public void deleteTask(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowsDeleted = 0;

        try {
            conn = dbManager.openConnection();
            String sql = "DELETE FROM Tasks WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rowsDeleted = pstmt.executeUpdate();

            // Generate HTML response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rowsDeleted > 0) {
                out.println("<h3>Task deleted successfully!</h3>");
            } else {
                out.println("<h3>No task found with ID: " + id + "</h3>");
            }

            out.println("<br><a href='displayTasks'>View All Tasks</a> | <a href='index.html'>Back to Home</a>");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle error response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3>Error deleting task.</h3>");
            out.println("<br><a href='index.html'>Back to Home</a>");
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
