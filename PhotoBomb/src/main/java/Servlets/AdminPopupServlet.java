/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.DatabaseController;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KJ
 */
public class AdminPopupServlet extends HttpServlet{
    @Override 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String updateId, message, deleteId, url;
        DatabaseController database = new DatabaseController();
        url = "/admin.jsp";
        message = "";
        try{
            updateId = request.getParameter("update");
            deleteId = request.getParameter("delete");
            if(updateId != null ){
                database.connectToDatabase();
                request.setAttribute("userUpdate", database.getUserInfo(updateId));
                request.setAttribute("admin-popup","update");
            }
            else if(deleteId != null){
                database.connectToDatabase();
                request.setAttribute("userDelete", database.getUserInfo(deleteId));
                request.setAttribute("admin-popup","delete");
            }
        }
        catch(NullPointerException | SQLException ex){
            message = "could not process operation " + ex;
        }
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    
     @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        
    }
}
