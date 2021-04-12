/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;
import Database.DatabaseController;
import Classes.Validation;
import Classes.User;
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
public class LoginServlet extends HttpServlet{
    @Override 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Validation valid = new Validation();
        String url = "/index.jsp";
        String username; // used to store username
        String password; // used to store password
        String message  = ""; // used to store error message
        
        try{ // Validate user input
            username = (String) request.getParameter("username").trim().toLowerCase(); // fetch username
            password = (String) request.getParameter("password"); // fetech password
            
            if(!valid.allFilled(new String[]{username,password})){ // ensure all fields are filled
                message = "Please Fill All Fields";
            }
            else{
                if(!valid.minInputLength(password, 6)){ // Ensure input meets min length requirement
                    message = "password must be 6 or more characters";
                }
                else{                    
                    DatabaseController database = new DatabaseController();// Instantiate database object
                    User user = new User(username, password); // instantiate user object
                    database.connectToDatabase(); // connect to database
                    String userId = database.verifyLoginInfo(user); // verify login information
                    if(userId == null){
                        message = "username or password is incorrect"; // message if user information does not match records
                    }
                    else{                                     
                        HttpSession session = request.getSession(); // Establish session object                        
                        User userInfo =  database.getUserInfo(userId); // get user profile infformation
                        session.setAttribute("user", userInfo); // set session object
                        session.setAttribute("userPosts", database.getPosts(null));// Set post attribute for processing in jsp 
                        if(database.isAdmin(userId)){
                            url = "/admin.jsp";// Url to redirect to admin page 
                            session.setAttribute("admin", true);
                            session.setAttribute("userList", database.getUsers());                            
                        }
                        else{
                            url = "/home.jsp"; // Url to redirect to home page  
                            session.setAttribute("admin", false);
                        }
                        database.closeConn();// close connection
                    }                  
                }
            }
        }
        catch(NullPointerException | SQLException | NoSuchAlgorithmException ex){
            message = "could not process login ";          
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    
    @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
    }
    
}
