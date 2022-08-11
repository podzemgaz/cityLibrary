<%--
  Created by IntelliJ IDEA.
  User: podze
  Date: 08.08.2022
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome page</title>
</head>
<body>
    <c:if test="${empty sessionScope.user}">
        <jsp:forward page="login.jsp"/>
    </c:if>
    <h3>Welcome</h3>
    <hr>
    ${sessionScope.user}, hello!
    <hr>
    <a href="controller?command=logout">Logout</a>
</body>
</html>
