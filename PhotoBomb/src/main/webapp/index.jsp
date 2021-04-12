<%-- 
    Document   : index
    Created on : Mar 17, 2021, 5:28:40 PM
    Author     : Kary Johnson
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<jsp:include page="navigation/front-header.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>Login</title>
    </head>
    <body>
        <div class="front-page">
            <div class="overlay">                 
                 <div class="form-box">
                    <h1 class="heading">Login</h1> 
                    <form action="loginServlet" method="post">
                        <input type="text" class="custom-input" name="username" 
                               placeholder="username" required="true" minlength="6">
                        <input type="password" class="custom-input" name="password"
                               placeholder="password" required="true" >
                        <button class="custom-btn" type="submit">submit</button>
                        <a class="link" href="signup.jsp">sign up</a>
                        <p class="error-msg">${message}</p>
                    </form>                   
                 </div>
            </div>
        </div>
    </body>
</html>
