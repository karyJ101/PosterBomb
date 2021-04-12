<%-- 
    Document   : home
    Created on : Mar 20, 2021, 7:56:02 PM
    Author     : Kary Johnosn
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<Map> userPosts = (ArrayList<Map>) session.getAttribute("userPosts");
    if(userPosts != null){
        request.setAttribute("userPosts", userPosts);
    }
%>
<jsp:include page="session-check.jsp"/>
<jsp:include page="navigation/inner-header.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>Feed</title>
    </head>
    <body>
        <h1>${message}</h1>
            <div class="content-box">                
                <c:forEach var = "userPost" items="${userPosts}">
                    <div class="post-box">                        
                        <div class="poster-box">@${userPost.get("username")}</div>                        
                        ${userPost.get("post")}
                        <div class="comm-align">
                            <form action="commentsServlet" method="post">
                                <input type="hidden" name="postId" value="${userPost.get("postId")}">
                                <button class="upload-btn" id="view-comm-btn" type="submit">comment</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>            
    </body>
    <jsp:include page="navigation/footer.jsp"/>
</html>
