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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kary Johnson
 */
public class UpdateDeleteServlet extends HttpServlet{
    @Override 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        Validation valid = new Validation();
        DatabaseController database = new DatabaseController();
        HttpSession session = request.getSession();
        User user = new User();
        String first, last, email, username, password, 
               reEnterPass, url, message, updateId, deleteId; 
              
        url = "/admin.jsp";
        message = "";
        String[] errorMsg = {"Please Enter A Valid Last Name",
                             "Please Enter A Valid Email",
                             "Please Enter At least 6 Characters",
                             "Please Enter A Valid Username",
                             "Please Enter A Valid Password",
                             "Passwords Do Not Match"};  
        boolean error = false;
        try{
            updateId = request.getParameter("updateId");
            deleteId = request.getParameter("deleteId");
            
            if(deleteId != null){
                database.connectToDatabase();
                database.deleteUserInfo(deleteId);
            }
            else if(updateId != null){                
                database.connectToDatabase();
                first       = request.getParameter("firstname").trim();
                last        = request.getParameter("lastname").trim();
                email       = request.getParameter("email").trim().toLowerCase();
                username    = request.getParameter("username").trim().toLowerCase();
                password    = request.getParameter("password").trim();
                reEnterPass = request.getParameter("reEnterPass").trim();
                user        = database.getUserInfo(updateId);
                
                if(first != null && !first.isEmpty()){                         
                    if(!valid.invalidChars(first)){
                        user.setFirstname(errorMsg[0]);
                        error = true;
                    }
                    else{
                        user.setFirstname(first);
                        database.updateUserInfo(first, "firstname", updateId, "user_profile"); 
                    }                                      
                }
                
                if(last != null && !last.isEmpty()){                      
                    if(!valid.invalidChars(last) || last.equals(errorMsg[0])){
                        user.setLastname(errorMsg[0]);
                        error = true;
                    }
                    else{
                        user.setLastname(last);
                        database.updateUserInfo(last, "lastname", updateId, "user_profile"); 
                    }                                      
                }
                
                if(email != null && !email.isEmpty()){    
                    if(!email.equals(user.getEmail())){
                        User checkEmail = new User();
                        checkEmail.setEmail(email);
                        String emailInUse = database.userExist(checkEmail);
                        
                        if(!valid.emailPattern(email)){
                            user.setEmail(errorMsg[1]);
                            error = true;
                        }
                        else if(!emailInUse.equals("")){
                            user.setEmail(emailInUse);
                            error = true;
                        }
                        else{
                            database.updateUserInfo(email, "email", updateId, "user_profile");
                        } 
                    }
                }
                
                if(username != null&& !username.isEmpty()){
                    if(!username.equals(user.getUsername())){
                        User checkUsername = new User();
                        checkUsername.setUsername(username);
                        String usernameTaken = database.userExist(checkUsername);
                        
                        if(!usernameTaken.equals("")){
                            user.setUsername(usernameTaken);
                            error = true;
                        }
                        else if(username.contains(" ")){
                            user.setUsername(errorMsg[3]);
                            error = true;
                        }
                        else if(!valid.minInputLength(username, 6)){
                            user.setUsername(errorMsg[2]);
                            error = true;
                        }
                        else{
                            database.updateUserInfo(username, "username", updateId, "users");
                        }
                    }
                }
                if(password != null && !password.isEmpty()){   
                    log("Password: " + password);
                    if(!valid.minInputLength(password, 6)){
                        request.setAttribute("passError", errorMsg[2]);
                        error = true;
                    }
                    else if(password.contains(" ")){
                        request.setAttribute("passError", errorMsg[4]);
                        error = true;
                    }                    
                    
                }
                if(reEnterPass != null && !reEnterPass.isEmpty()){               
                    if(!valid.inputMatch(reEnterPass, password)){                        
                        request.setAttribute("reEnter", errorMsg[5]);                        
                        error = true;                    
                    }
                    else{
                        String hashedPass = valid.hashPassword(password);
                        database.updateUserInfo(hashedPass, "password", updateId, "users");
                    }
                }        

                if(error){
                    request.setAttribute("admin-popup","update");
                }
            }            
            request.setAttribute("userUpdate", user);           
            session.setAttribute("userList", database.getUsers());            
        }
        catch(NullPointerException | SQLException | NoSuchAlgorithmException ex){
            message = "could not process operation " + ex;
            request.setAttribute("admin-popup","update");
        }
        request.setAttribute("message", message);        
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    
     @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        
    }
    
    
}
