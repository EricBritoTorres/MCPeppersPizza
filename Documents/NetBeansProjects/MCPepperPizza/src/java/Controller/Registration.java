/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Registration servlet to handle user sign-up.
 * Handles both GET and POST requests.
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display registration form (could also be done using a JSP)
        response.setContentType("text/html");
        response.getWriter().write(
                "<!DOCTYPE html>" +
                "<html>" +
                "<head><title>Register</title></head>" +
                "<body>" +
                "<h1>Register</h1>" +
                "<form method='post' action='Registration'>" +
                "Username: <input type='text' name='username' required/><br/>" +
                "Password: <input type='password' name='password' required/><br/>" +
                "<input type='submit' value='Register'/>" +
                "</form>" +
                "</body>" +
                "</html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.getWriter().write("Please fill all the fields.");
            return;
        }

       
        boolean userExists = usuarios.stream()
                .anyMatch(user -> user.getUsername().equals(username));

        if (userExists) {
            response.getWriter().write("Username already exists. Please choose another.");
        } else {
           
            Usuario newUser = new Usuario(username, password);
            usuarios.add(newUser);
            response.getWriter().write("User registered successfully!");
        }
    }
}
