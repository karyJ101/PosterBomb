<%-- 
    Document   : popup
    Created on : Mar 21, 2021, 8:48:38 PM
    Author     : Kary Johnson
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>popup</title>
    </head>       
        <% String popup = (String) request.getAttribute("popup");
           if(popup != null){%>
    <body onload="togglePopup('pop-up')"></body>
            <%} %>
    <body>       
        <div class="popup" id="pop-up">
            <div class="pop-overlay">
                <div class="pop-container">
                    <div class="pop-content">
                        <button onclick="togglePopup('pop-up')" class="close-btn">x</button>                        
                        <form action="postServlet" method="post">
                            <textarea class="cust-text-area" 
                                      placeholder="post up to 120 characters" 
                                      name="post" maxlength="120"></textarea>                            
                            <button class="custom-btn">submit</button>
                            <p class="error-msg">${message}</p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="javascript/javascript-functions.js"></script>
</html>
