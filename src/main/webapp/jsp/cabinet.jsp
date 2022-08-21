<%--
  Created by IntelliJ IDEA.
  User: podze
  Date: 16.08.2022
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.my.entity.Role" %>
<html>
<head>
    <title>Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:if test="${empty sessionScope.user}">
        <jsp:forward page="main.jsp"/>
    </c:if>
    <h3>Cabinet</h3>
    <%@include file="/jspf/header.jspf"%>
    <div>
        id: ${sessionScope.user.id}<br>
        login: ${sessionScope.user.login}<br>
        role: ${Role.getRole(sessionScope.user)}
    </div>
</body>
</html>
