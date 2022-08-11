<%--
  Created by IntelliJ IDEA.
  User: podze
  Date: 08.08.2022
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Error page</title>
</head>
<body>
  Request from ${pageContext.errorData.requestURI} is failed
  <br/>
  Servlet name or type: ${pageContext.errorData.servletName}
  <br/>
  Status code: ${pageContext.errorData.statusCode}
  <br/>
  Exception: ${pageContext.errorData.throwable.stackTrace}
</body>
</html>
