package com.itsc.exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itsc.exam.dao.DBConnectionManager;

@Controller
public class SearchTaskServlet {
	
	@Autowired
    private DBConnectionManager dbManager;

    @GetMapping("/searchTask")
    public void searchTask(@RequestParam("description") String description,
                           HttpServletResponse response) throws IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbManager.openConnection();
            String sql = "SELECT * FROM Tasks WHERE description LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + description + "%");
            rs = pstmt.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Search Results</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Description</th><th>Status</th><th>Due Date</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String desc = rs.getString("description");
                String status = rs.getString("status");
                java.sql.Date dueDate = rs.getDate("due_date");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + desc + "</td>");
                out.println("<td>" + status + "</td>");
                out.println("<td>" + dueDate + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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