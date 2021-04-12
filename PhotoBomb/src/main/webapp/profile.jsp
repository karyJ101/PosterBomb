<%-- 
    Document   : profile
    Created on : Mar 23, 2021, 4:24:07 PM
    Author     : Kary Johnson
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<jsp:include page="navigation/inner-header.jsp"/>
<jsp:include page="session-check.jsp"/>
<!DOCTYPE html>
<html>
    <head>
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>Profile</title>
    </head>
    <body>
        <div class="profile-display">
            <h1>username: @${user.getUsername()}</h1>
            <h1>first name: ${user.getFirstname()}</h1>
            <h1>last name: ${user.getLastname()}</h1>
            <h1>email: ${user.getEmail()}</h1>
        </div>
    </body>
    <jsp:include page="navigation/footer.jsp"/>
</html>
