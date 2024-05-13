package org.example.webapps;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/CATSignupServlet")
public class CATSignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // JDBC code to insert new user
        // Assuming you have a table named 'users' with columns 'username', 'password', and 'email'

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dba", "root", "rootpass");
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("cat_login.jsp");
            } else {
                // Handle signup failure
                response.sendRedirect("cat_signup.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
