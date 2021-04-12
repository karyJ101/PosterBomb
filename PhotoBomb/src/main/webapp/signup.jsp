<%-- 
    Document   : signup
    Created on : Mar 18, 2021, 10:26:19 AM
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
        <title>Sign Up</title>
    </head>
    <body>
        <div class="front-page">
            <div class="overlay">                 
                 <div class="form-box">
                    <h1 class="heading">Sign up</h1> 
                    <form action="signUpServlet" method="post">
                        <input type="text" class="custom-input" name="firstname" 
                               placeholder="firstname" required="true">
                        <input type="text" class="custom-input" name="lastname"
                               placeholder="lastname" required="true">
                        <input type="email" class="custom-input" name="email" 
                               placeholder="email address" required="true">
                        <input type="text" class="custom-input" name="username" 
                               placeholder="username" required="true" minlength="6">
                        <input type="password" class="custom-input" name="password"
                               placeholder="password" required="true" minlength="6">
                         <input type="password" class="custom-input" name="re-enter-pass"
                               placeholder="re-enter password" required="true" minlength="6">
                        <button class="custom-btn" type="submit">submit</button>
                        <a class="link" href="index.jsp">login</a>
                         <p class="error-msg">${message}</p>
                    </form>                   
                 </div>
            </div>
        </div>
    </body>
</html>
