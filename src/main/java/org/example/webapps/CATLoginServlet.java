package org.example.webapps;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/CATLoginServlet")
public class CATLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // JDBC code to authenticate user
        // Assuming you have a table named 'users' with columns 'username' and 'password'

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dba", "root", "rootpass");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard.jsp?sessionId=" + session.getId());
            } else {
                // Redirect to error.jsp if login credentials are incorrect
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Redirect to error.jsp if any database error occurs
            response.sendRedirect("error.jsp");
        }
    }
}


