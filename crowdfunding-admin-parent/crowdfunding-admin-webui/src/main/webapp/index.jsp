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
    <script type="text/javascript" src="layer/layer.js"></script>
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

            var array = [5,8,10];
            var requestBody = JSON.stringify(array);
            $("#btn2").click(function() {
                $.ajax({
                    url: "test/sendArray/two.html",
                    type: "post",
                    dataType: "text",
                    data: requestBody,
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        alert(data);
                    },
                    error: function (data) {
                        alert(data);
                    }
                })
            });

            $("#btn3").click(function() {

                var student = {
                    "stuId":1,
                    "stuName": "tom",
                    "stuAge": "20",
                    "address": {
                        "province": "江西",
                        "city": "南昌",
                        "street": "青山湖大道"
                    },
                    "subjectList": [
                        {
                            "name": "语文",
                            "score": 70
                        },{
                            "name": "数学",
                            "score": 90
                        }
                    ]
                };
                var requestBody = JSON.stringify(student);

                $.ajax({
                    url: "test/sendComposeData/three.html",
                    type: "post",
                    dataType: "text",
                    data: requestBody,
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        alert(data);
                    },
                    error: function (data) {
                        alert(data);
                    }
                })
            });

            $("#btn4").click(function() {

                var student = {
                    "stuId":1,
                    "stuName": "tom",
                    "stuAge": "20",
                    "address": {
                        "province": "江西",
                        "city": "南昌",
                        "street": "青山湖大道"
                    },
                    "subjectList": [
                        {
                            "name": "语文",
                            "score": 70
                        },{
                            "name": "数学",
                            "score": 90
                        }
                    ]
                };
                var requestBody = JSON.stringify(student);

                $.ajax({
                    url: "test/sendComposeData/object.json",
                    type: "post",
                    dataType: "json",
                    data: requestBody,
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        alert(data);
                    },
                    error: function (data) {
                        alert(data);
                    }
                })
            });

            $("#btn5").click(function () {
               /* alert("cdd");*/
                layer.msg("layer弹框");
            });
        })
    </script>
</head>
<body>
    <a href="test/ssm.html">测试</a>
    <br/>

    <button id="btn1">test array1</button>
    <br/>

    <button id="btn2">test array2</button>
    <br/>

    <button id="btn3">test array2</button>

    <br/>

    <button id="btn4">test ResultEntity</button>
    <br/>

    <button id="btn5">layer弹框测试</button>

</body>
</html>
