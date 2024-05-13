package org.example.webapps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ApplyServlet")
public class ApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Get job ID from the request
            int jobId = Integer.parseInt(request.getParameter("jobId"));

            // Get username from session
            String username = (String) request.getSession().getAttribute("username");

            // Insert job application into the database
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dba", "root", "rootpass");
                PreparedStatement ps = con.prepareStatement("INSERT INTO job_applications (job_id, username) VALUES (?, ?)");
                ps.setInt(1, jobId);
                ps.setString(2, username);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    out.println("Application submitted successfully!");
                } else {
                    out.println("Failed to submit application!");
                }
                con.close();
            } catch (SQLException e) {
                out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}

