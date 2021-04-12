<%-- 
    Document   : admin-session-check
    Created on : Apr 1, 2021, 2:13:31 PM
    Author     : Kary Johnson
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<% 
    boolean isAdmin = (boolean) session.getAttribute("admin");
    if(!isAdmin){
        RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
        redirect.forward(request, response);
    }
%>
<!DOCTYPE html>

