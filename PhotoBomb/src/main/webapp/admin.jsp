<%-- 
    Document   : admin
    Created on : Mar 31, 2021, 11:03:13 AM
    Author     : Kary Johnson
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<jsp:include page="session-check.jsp"/>
<jsp:include page="navigation/inner-header.jsp"/>
<jsp:include page="adminpopup.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<Map> userList = (ArrayList<Map>) session.getAttribute("userList");
    if(userList != null){
        request.setAttribute("userList", userList);
    }
    else{
        request.setAttribute("message", "no users found");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="css/styles.css">
        <title>Administrator</title>
    </head>
    <body>
        <div class="content-box">
            <table>
                <thead>
                    <tr>
                        <th>UserId</th>
                        <th>First Name</th>
                        <th>Last Lame</th>
                        <th>Email</th>
                        <th>Username</th>                        
                        <th>Operations</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.get("userId")}</td>
                            <td>${user.get("firstname")}</td>
                            <td>${user.get("lastname")}</td>                            
                            <td>${user.get("email")}</td>
                            <td>${user.get("username")}</td>                        
                        <td id="operations">
                            <form action="adminPopupServlet" method="post">
                                <button 
                                    name="update"
                                    value="${user.get("userId")}"                                       
                                    class="operate-btn">update
                                </button>
                            </form>
                            <form action="adminPopupServlet" method="post">
                                <button 
                                    name="delete"
                                    value="${user.get("userId")}"
                                    class="operate-btn">delete                                    
                                </button>
                            </form>
                        </td>
                        </tr>
                    </c:forEach>               
                </tbody>
            </table>
        </div>
    </body>
    <jsp:include page="navigation/footer.jsp"/>
</html>
