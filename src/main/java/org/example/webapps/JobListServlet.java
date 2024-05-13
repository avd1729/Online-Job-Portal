package org.example.webapps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JobListServlet")
public class JobListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String stream = request.getParameter("stream");

            // JDBC code to retrieve job postings based on the selected stream
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dba", "root", "rootpass");
                PreparedStatement ps = con.prepareStatement("SELECT * FROM job_listings WHERE stream=?");
                ps.setString(1, stream);
                ResultSet rs = ps.executeQuery();

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Job Postings</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Job Postings for " + stream + " Stream</h2>");
                out.println("<table border=\"1\">");
                out.println("<tr><th>Title</th><th>Description</th></tr>");
                while (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    int jobId = rs.getInt("id"); // Assuming job id is retrieved from the database
                    out.println("<tr><td>" + title + "</td><td>" + description + "</td><td><form action=\"ApplyServlet\" method=\"post\">" +
                            "<input type=\"hidden\" name=\"jobId\" value=\"" + jobId + "\">" +
                            "<input type=\"submit\" value=\"Apply\">" +
                            "</form></td></tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");

                con.close();
            } catch (SQLException e) {
                out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}

