<%-- 
    Document   : session-check
    Created on : Mar 21, 2021, 5:51:05 PM
    Author     : Kary Johnson
--%>

<%@page import="Classes.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%
    User userSession = (User) session.getAttribute("user");
    if(userSession == null){       
       RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
       redirect.forward(request, response);
    }
%>

