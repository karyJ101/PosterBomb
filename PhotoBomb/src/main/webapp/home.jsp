<%-- 
    Document   : home
    Created on : Mar 25, 2021, 7:12:34 PM
    Author     : Kary Johnson
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<jsp:include page="session-check.jsp"/>
<jsp:include page="navigation/inner-header.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>Home</title>
    </head>
    <body>
        <div class="banner">
            <h1 id="welcome">Welcome</h1>
        </div>
    </body>
    <jsp:include page="navigation/footer.jsp"/>
</html>
