<%-- 
    Document   : comments
    Created on : Mar 23, 2021, 4:23:39 PM
    Author     : Kary Johnson
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="session-check.jsp"/>
<jsp:include page="navigation/inner-header.jsp"/>
<!DOCTYPE html>
<html>
    <head>        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>Comments</title>
    </head>
    <body>
        <div class="content-box">              
            <div class="originalPost">
                 <div class="poster-box">@${originalPost.get(0).get("username")}</div>  
                ${originalPost.get(0).get("post")}
            </div>
            
            <div class="comment-box">
                <div class="post-comm-box">
                    <form action="commentsServlet" method="post">  
                        <input type="hidden" name="postId" 
                               value="${originalPost.get(0).get("postId")}">
                        <textarea class="comm-text-area"                                      
                                  placeholder="post up to 120 characters"                                       
                                  name="comment" maxlength="120"></textarea>                           
                        <div class="comm-align">
                            <button class="upload-btn">submit</button>
                        </div>                         
                        <p class="error-msg">${message}</p>                        
                    </form>
                </div>                    
            </div> 
                    
                <c:forEach var = "userComment" items="${userComments}">
                    <div class="post-box">
                        <img src="">
                        <div class="poster-box">@${userComment.get("username")}</div>                        
                        ${userComment.get("comment")}
                    </div>
                </c:forEach>
            </div>          
    </body>
    <jsp:include page="navigation/footer.jsp"/>
</html>
