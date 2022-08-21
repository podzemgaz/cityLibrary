<%--
  Created by IntelliJ IDEA.
  User: podze
  Date: 06.08.2022
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Login Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:if test="${not empty sessionScope.user}">
        <c:redirect url="/jsp/main.jsp"/>
    </c:if>
    <h3>Login</h3>
    <%@include file="/jspf/header.jspf"%>
    <div>
        <form method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login">
            <input type="text" name="login" placeholder="Login" /><br>
            <input type="password" name="password" placeholder="Password" /><br>
            <input type="submit" value="Login">
        </form>
    </div>

   <%@include file="/jspf/error.jspf"%>
</body>
</html>
