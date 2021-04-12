<%-- 
    Document   : inner-header
    Created on : Mar 21, 2021, 6:28:33 PM
    Author     : Kary Johnson
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<jsp:include page="popup.jsp"/>
<% 
     boolean isAdmin = false;
    if(session.getAttribute("admin") != null){
        isAdmin = (boolean) session.getAttribute("admin");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/nav-styles.css">
        <title>inner header</title>
    </head>
    <body>
        <div class="inner-menu-bar">
            <a class="logo" href="home.jsp"><h1>PosterBomb</h1></a>
            <div class="right-nav"> 
                <% if(isAdmin){%>
                <a class="nav-item" id="admin" href="admin.jsp">admin</a>
                <%}%>
                <a class="nav-item" id="home" href="home.jsp">home</a>
                <a class="nav-item" id="feed" href="feed.jsp">feed</a> 
                <a class="nav-item" id="profile" href="profile.jsp">profile</a>            
                <form class="nav-item" action="logoutServlet" method="post">                        
                    <button class="nav-logout" id="logout">logout</button>                    
                </form>                
                <div class="nav-item"><h1 class="user">@${user.getUsername()}</h1></div>                  
                 <div class="nav-item">
                     <button onclick="togglePopup('pop-up')" class="upload-btn">post</button>
                 </div>
            </div>
        </div>        
    </body>
</html>
