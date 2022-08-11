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
</head>
<body>
    <c:if test="${not empty sessionScope.user}">
        <jsp:forward page="main.jsp"/>
    </c:if>
    <h2>Login</h2>
    <form method="post" action="controller">
      <input type="hidden" name="command" value="login">
        <label>
            <input type="text" name="login" placeholder="Login" />
        </label>
        <label>
            <input type="password" name="password" placeholder="Password" />
        </label>
        <input type="submit" value="Login">
    </form>
    <a href="${pageContext.request.contextPath}/index.jsp">Register</a>

   <%@include file="/jspf/error.jspf"%>
</body>
</html>
