<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="js/role.js"></script>
<script type="text/javascript">
    $(function () {
        //1、为分页初始化数据
        window.keyword = "";
        window.pageNum = 1;
        window.pageSize = 5;

        generatePage();


        // 3.给查询按钮绑定单击响应函数
        $("#searchBtn").click(function () {
            // ①获取关键词数据赋值给对应的全局变量
            window.keyword = $("#keywordInput").val();
            // ②调用分页函数刷新页面
            generatePage();
        });


        //打开添加角色模态框
        $("#openAddRoleModalBtn").click(function () {
            $('#addRoleModal').modal('show');
        });

        //添加角色
        $("#addRoleBtn").click(function () {
            //获取roleName的值
            var roleName = $("#addRoleModal [name = roleName]").val();

            $.ajax({
                url: "role/add.json",
                type: "post",
                data: {
                    "name": roleName
                },
                dataType: "json",
                success: function (response) {
                    //返回的结果是成功的
                    if (response.result == "SUCCESS") {
                        layer.msg("添加成功");

                        //将pageNum调到最后一页
                        window.pageNum = 99999999;
                        //更新分页
                        generatePage();
                    }

                    if (response.result == "FAILED") {
                        layer.msg("添加失败" + response.message);
                    }
                },
                error: function (response) {
                    layer.msg("添加失败" + response.status + response.statusText);
                }
            });

            //关闭模态框
            $("#addRoleModal").modal("hide");

            //清理表单数据
            $("#addRoleModal [name = roleName]").val("");
        });

        //给每个角色的修改按钮绑定事件，需要用on函数，传统方式只适用于第一页
        $("#rolePageBody").on("click", ".pencilBtn", function () {
            $("#editRoleModal").modal("show");

            //获取到roleName的值
            var roleName = $(this).parent().prev().text();

            //获取角色id，并放入全局变量中
            window.id = this.id;

            //将roleName的值放入表单中
            $("#editRoleModal [name = roleName]").val(roleName);
        });

        //修改按钮绑定事件
        $("#editRoleBtn").click(function () {
            //获取修改后的roleName
            var roleName = $("#editRoleModal [name = roleName]").val();
            $.ajax({
                url: "role/update.json",
                type: "post",
                data: {
                    "id": window.id,
                    name: roleName
                },
                dataType: "json",
                success: function (response) {
                    //返回的结果是成功的
                    if (response.result == "SUCCESS") {
                        layer.msg("修改成功");

                        //更新分页
                        generatePage();
                    }

                    if (response.result == "FAILED") {
                        layer.msg("修改失败" + response.message);
                    }
                },
                error: function (response) {
                    layer.msg("修改失败" + response.status + response.statusText);
                }
            });
            //关闭模态框
            $("#editRoleModal").modal("hide");
        });

        //确认删除按钮绑定事件
        $("#removeRoleBtn").click(function () {

            //将roleIdArray封装为json数据
            var requestbody = JSON.stringify(window.roleIdArray);
            console.log(requestbody);

            $.ajax({
                url: "role/roleIdArray/remove.json",
                type: "post",
                data: requestbody,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (response) {
                    //返回的结果是成功的
                    if (response.result == "SUCCESS") {
                        layer.msg("删除成功");

                        //更新分页
                        generatePage();
                    }

                    if (response.result == "FAILED") {
                        layer.msg("删除失败" + response.message);
                    }
                },
                error: function (response) {
                    layer.msg("删除失败" + response.status + response.statusText);
                }
            });
            //关闭模态框
            $("#removeRoleModal").modal("hide");
        });

        //单条删除绑定单击响应
        $("#rolePageBody").on("click", ".removeBtn", function () {

            //获取roleId
            var roleId = this.id;
            //获取roleName
            var roleName = $(this).parent().prev().text();

            var roleArray = [{"roleId": roleId, "roleName": roleName}];

            //调用确认删除模态框函数
            confirmRemoveModal(roleArray);
        });

        //全选框
        $("#sumarryChexbox").click(function () {
            //确认当前状态
            var currentStatus = this.checked;

            //用当前多选框状态设置其它多选框
            $(".itemBox").prop("checked",currentStatus);
        });

        //其它多选框全选中则全选框选中，否则没有选中
        $("#rolePageBody").on("click", ".itemBox", function () {
            //获取选中的数量
            var checkedLength = $(".itemBox:checked").length;
            //获取全部多选框的数量
            var totalLength = $(".itemBox").length;

            //设置全选框的状态
            $("#sumarryChexbox").prop("checked", checkedLength == totalLength);
        });

        //批量删除绑定单击事件
        $("#removeBtn").click(function () {

            var roleIdArray = [];

            //遍历已选中的数据
            $(".itemBox:checked").each(function () {
                //获取roleId
                var roleId = this.id;
                //获取roleName
                var roleName = $(this).parent().next().text();

                //将数据添加到roleIdArray中
                roleIdArray.push({"roleId": roleId, "roleName": roleName});
            });
            //判断roleIdArray是否为空
            if (roleIdArray.length == 0) {
                layer.msg("请先选中一条数据");
                return;
            }
            confirmRemoveModal(roleIdArray);
        });


    })
</script>

<body>

<%@include file="/WEB-INF/include/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="removeBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;" id="openAddRoleModalBtn"
                    ><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="sumarryChexbox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <%--jquery.pagination使用分页--%>
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/role-add-modal.jsp" %>
<%@include file="/WEB-INF/include/role-edit-modal.jsp" %>
<%@include file="/WEB-INF/include/role-remove-modal.jsp" %>
</body>
</html>