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
    <title>Main</title>
</head>
<body>
    <h3>Main page</h3>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <div>
            ${sessionScope.user.login}, hello!
                <c:if test="${sessionScope.role eq \"admin\"}">
                    <a href="controller?command=userList">Users</a>
                </c:if>
                <a href="controller?command=logout">Logout</a>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
