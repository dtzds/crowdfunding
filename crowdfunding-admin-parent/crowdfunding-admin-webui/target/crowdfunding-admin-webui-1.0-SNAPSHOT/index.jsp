<%--
  Created by IntelliJ IDEA.
  User: EWRT
  Date: 2020/4/27
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script>
        $(function() {
            $("#btn1").click(function() {
                $.ajax({
                    url: "test/sendArray/one.html",
                    type: "post",
                    dataType: "text",
                    data: {
                        "array": [1,2,3]
                    },
                    success: function (data) {
                        alert(data);
                    },
                    error: function (data) {
                        alert(data);
                    }
                })
            });
        })
    </script>
</head>
<body>
    <a href="test/ssm.html">测试</a>

    <button id="btn1">test array</button>
</body>
</html>
