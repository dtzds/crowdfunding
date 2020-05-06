<%--
  Created by IntelliJ IDEA.
  User: EWRT
  Date: 2020/5/3
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 从请求域中取出exception对象，再进一步访问message就可以获得异常数据 --%>
    ${requestScope.exception.message}
</body>
</html>
