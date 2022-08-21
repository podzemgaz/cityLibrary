<%--
  Created by IntelliJ IDEA.
  User: podze
  Date: 17.08.2022
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.my.entity.Role" %>
<%@ page import="com.my.entity.Status" %>
<html>
<head>
    <title>Users</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <c:if test="${empty sessionScope.user}">
    <jsp:forward page="main.jsp"/>
  </c:if>
  <h3>Users</h3>
  <%@include file="/jspf/header.jspf"%>
  <c:choose>
    <c:when test="${not empty requestScope.users}">
      <div>
        <form action="${pageContext.request.contextPath}/controller" method="post">
          <table>
            <tr>
              <th colspan="5">Users:</th>
            </tr>
            <tr>
              <th>id</th>
              <th>login</th>
              <th>role</th>
              <th>status</th>
              <th>select</th>
            </tr>

            <c:forEach items="${requestScope.users}" var="user">
              <c:if test="${user.id ne sessionScope.user.id}">
                <tr>
                  <td>
                    <c:out value="${user.id}"/>
                  </td>
                  <td>
                    <c:out value="${user.login}"/>
                  </td>
                  <td>
                    <c:out value="${Role.getRole(user)}"/>
                  </td>
                  <td>
                    <c:out value="${Status.getStatus(user)}"/>
                  </td>
                  <td><input type="radio" name="user_id" value="${user.id}"></td>
                </tr>
              </c:if>
            </c:forEach>
          </table>
          <select name="command">
            <option value="moder">moder/unmoder</option>
            <option value="block">block/unblock</option>
          </select>
          <input type="submit" value="update this user">
        </form>
      </div>
    </c:when>
    <c:otherwise>
      <jsp:include page="/controller?command=users"/>
    </c:otherwise>
  </c:choose>

</body>
</html>
