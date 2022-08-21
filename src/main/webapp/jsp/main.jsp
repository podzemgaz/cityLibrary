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
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h3>Home</h3>
   <%-- <%=request.getRequestURI()%>--%>
    <%@include file="/jspf/header.jspf"%>
    <%@include file="/jspf/error.jspf"%>
</body>
</html>
