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
import java.util.InputMismatchException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KJ
 */
public class CommentsServlet extends HttpServlet{
    @Override 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String postId, comment, userId; // Used to store information for querying comment database
        User user; // used to store user session object
        String url = "/comments.jsp"; // comments page
        String message = "";
        HttpSession session = request.getSession(); // Instantite session object
        Validation valid = new Validation(); // used to validate input
        try{
            postId  = (String) request.getParameter("postId"); // Get post id 
            comment = (String) request.getParameter("comment");// get user comment
            user    = (User) session.getAttribute("user"); // get user session object
            if(postId != null && user != null){ // check if post id and user session object is not null
                userId = user.getUserId(); // get session user id
                DatabaseController database = new DatabaseController(); // Instatiate database object
                database.connectToDatabase();  // connect to database             
               if(comment != null){ //If there is a comment made, then validate
                   if(valid.maxInputLength(comment, 120) && !comment.equals("")){
                       database.postComment(userId, postId, comment);     // If comment meets requirements, post to database                     
                   }   
                   else{
                        message = "please enter a comment 120 characters or less";
                   }
               }
                request.setAttribute("userComments", database.getComments(postId));// get all comments
                request.setAttribute("originalPost", database.getPosts(postId)); // get original post
                database.closeConn();// close connection to database
            }
        }
        catch(SQLException | NullPointerException | InputMismatchException  ex){
            message = "Unable to process post ";
            log("Comment Error: " + ex);
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        
    }
}
