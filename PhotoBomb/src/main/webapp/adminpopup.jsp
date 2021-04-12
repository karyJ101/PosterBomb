<%-- 
    Document   : adminpopup
    Created on : Apr 1, 2021, 2:04:21 PM
    Author     : Kary Johnson
--%>

<%@page import="Classes.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%--<jsp:include page="admin-session-check.jsp"/>--%>
<% 
    String popup = (String) request.getAttribute("admin-popup");   
%>
<!DOCTYPE html>
<html>
   <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>admin-popup</title>
    </head>       
      
           <% if(popup != null){%>
    <body onload="togglePopup('admin-popup')"></body>
            <%} %>
    <body>       
        <div class="popup" id="admin-popup">
            <div class="pop-overlay">
                <div class="pop-container">
                    <div class="pop-content">
                        <button onclick="togglePopup('admin-popup')" class="close-btn">x</button>     
                        <% if(popup != null){
                            if(popup.equals("update")){%>
                            <form action="updateDeleteServlet" method="post"> 
                            <input class="custom-input" 
                                   value ="${userUpdate.getFirstname()}" 
                                   name="firstname" 
                                   placeholder="firstname">
                            
                            <input class="custom-input"
                                   value ="${userUpdate.getLastname()}" 
                                   name="lastname" 
                                   placeholder="lastname">
                            
                            <input class="custom-input" 
                                   value ="${userUpdate.getEmail()}" 
                                   name="email" 
                                   placeholder="email">
                            
                            <input class="custom-input" 
                                   value ="${userUpdate.getUsername()}" 
                                   name="username"
                                   placeholder="username">
                            
                            <input class="custom-input" 
                                   name="password"
                                   value="${passError}"
                                   placeholder="new password">
                            
                            <input class="custom-input" 
                                   name="reEnterPass"
                                   value ="${reEnter}"
                                   placeholder="re-enter password">
                            
                            <input class="custom-input" 
                                   type="hidden"
                                   value ="${userUpdate.getUserId()}" 
                                   name="updateId"
                                   placeholder="username">
                            
                            <button class="custom-btn"                                    
                                    name="confirmUpdate"
                                    type="submit">submit</button>
                            <p class="error-msg">${message}</p>
                        </form>
                            <%}else if(popup.equals("delete")){%>
                            <div class="align-left">
                                <h1>Confirm Delete</h1><br>
                                <h1>First Name: ${userDelete.getFirstname()}</h1>
                                <h1>Last Name: ${userDelete.getLastname()}</h1>
                                <h1>Email: ${userDelete.getEmail()}</h1>
                                <h1>Username: ${userDelete.getUsername()}</h1>
                            </div>
                            
                            <form action="updateDeleteServlet" method="post">                                 
                                <input class="custom-input" 
                                   type="hidden"
                                   value ="${userDelete.getUserId()}" 
                                   name="deleteId"
                                   placeholder="username">
                                <button class="custom-btn"                                    
                                    name="confirmDelete"
                                    type="submit">submit</button>
                            </form>
                            <%}}%>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="javascript/javascript-functions.js"></script>
</html>
