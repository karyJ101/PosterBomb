/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.User;
import Classes.Validation;
import Database.DatabaseController;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kary Johnosn
 */
public class PostServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException{
        HttpSession session = request.getSession();
        Validation valid = new Validation();// used for validation
        String url = "/feed.jsp";
        String message, post, userId;  
        User user;
        message = "";
        try{        
            post    = (String) request.getParameter("post").trim();
            user    = (User) session.getAttribute("user");
            
            if(user == null){// ensure session has been established
               message = "must be logged in";
               url = "/index.jsp";
            }
            else if(post == null || post.isEmpty() || post.equals("")){ // check if the post variable is not null or empty            
                message = "no post made"; //if empty display error
                request.setAttribute("popup", "true");               
            }
            else if(!valid.maxInputLength(post,120)){ // check if post meets length requirements
                message = "post must be a maximum of 120 characters";                   
                request.setAttribute("popup", "true");
            }
            else{
                userId = user.getUserId();// Gets session id
                DatabaseController database = new DatabaseController(); // instantiate database object
                database.connectToDatabase();// connect to database
                database.uploadPost(userId, post); // upload post to database
                session.setAttribute("userPosts", database.getPosts(null)); // extract updated list of posts for display             
                database.closeConn();// close connection to database
            }
        }
        catch(SQLException | NullPointerException ex){
            message = "unable to process post: ";
            request.setAttribute("popup", "true");      
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        
    }
}
