<%--
  Created by IntelliJ IDEA.
  User: podze
  Date: 12.08.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:if test="${not empty sessionScope.user}">
        <jsp:forward page="main.jsp"/>
    </c:if>
    <h3>Register page</h3>
    <%@include file="/jspf/header.jspf"%>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="registration">
        <input name="login" placeholder="login"><br>
        <input name="password" type="password" placeholder="password"><br>
        <input name="confirm_password" type="password" placeholder="confirm pass"><br>
        <input type="submit" value="Register">
    </form>
    <%@include file="/jspf/error.jspf"%>
</body>
</html>
