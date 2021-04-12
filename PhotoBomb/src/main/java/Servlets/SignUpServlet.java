/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.User;
import Classes.Validation;
import Database.DatabaseController;
import Utilities.GmailUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.MessagingException;

/**
 *
 * @author KJ
 */
public class SignUpServlet extends HttpServlet{
     @Override 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Validation valid = new Validation(); // Class used to handle all input validation        
        String firstname, lastname, email, username, password, reEnterPass; // Used to store user information
        String url = "/signup.jsp";
        String message; // used to store error messages for each field
        String sendTo, sendFrom, subject, body; // Used to store details for sending email
        sendFrom = "posterbombemail@gmail.com";
        
        try{ // Validate user input
            firstname = (String) request.getParameter("firstname").trim();
            lastname  = (String) request.getParameter("lastname").trim();
            email     = (String) request.getParameter("email").trim();
            username  = (String) request.getParameter("username").trim().toLowerCase();
            password  = (String) request.getParameter("password").trim();
            reEnterPass = (String) request.getParameter("re-enter-pass").trim();
            
            if(!valid.allFilled(new String[]{firstname, lastname, email, // checks if all required fields have been filled
                username, password, reEnterPass})){
                message = "please fill all fields";
            }
            else{                
                if(!valid.invalidChars(firstname)){ // checks for any invalid characters in the first name
                    message = "please enter only letters"; 
                }
                else if(!valid.invalidChars(lastname)){ // checks for any invalid characters in the last name
                    message = "please enter only letters";
                }
                else if(!valid.emailPattern(email)){ // check if the email matches the valid email pattern
                    message = "please enter a valid email address";
                }                
                else if(!valid.minInputLength(username,6)){ // checks if the entered username is the required length
                    message = "username must be 6 or more characters";
                }
                else if(!valid.minInputLength(password, 6)){// checks if the entered password is the required length
                    message = "password must be 6 or more characters";
                }
                else if (!valid.inputMatch(password, reEnterPass)){ // checks if the re-entered password matches 
                    message = "passwords entered do not match";
                }
                else{                    
                    DatabaseController database = new DatabaseController(); // Database controller object                    
                    database.connectToDatabase(); // Establish connection to database
                    password = valid.hashPassword(password); // hash entered password
                    User user = new User(firstname, lastname, email, username, password);
                    String checkUserExist = database.userExist(user);// check if account already exists
                    
                    if( !checkUserExist.equals("")){ // if user is found, display appropriate message
                        message = checkUserExist;
                    }
                    else{                        
                        database.createNewUser(user); // pass in user object with user info as arguments
                        sendTo  = user.getEmail();
                        subject = "Welcome To PosterBomb!";
                        body    = "Dear " + user.getFirstname() + 
                                "\n\n We just wanted to say thank you for joining "
                                + "posterbomb.";
                        message = "sign up successfull";   
                        GmailUtil.sendMail(sendTo, sendFrom, subject, body, false);
                    }
                                     
                }
            }
        }
        catch(NullPointerException | SQLException | NoSuchAlgorithmException |
                MessagingException ex){
            message = "could not process Signup " + ex;
            log("Error: " + ex);
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    
    @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
    }    
 
}
