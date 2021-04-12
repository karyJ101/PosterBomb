/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kary Johnson
 */
public class LogoutServlet extends HttpServlet {
        @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        HttpSession session = request.getSession();
        String url = "/index.jsp";
        User user  = (User) session.getAttribute("user"); // Check if valid session is found
        if(user != null){
            session.invalidate();// Invalidate all sessions
        }        
        getServletContext().getRequestDispatcher(url).forward(request, response); // redirect to login page
        
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{        
    }
}
