//打开确认删除模态框
function confirmRemoveModal(roleArray) {
    //打开模态框
    $("#removeRoleModal").modal("show");
    //清除旧数据
    $("#roleNameDiv").empty();

    window.roleIdArray = [];

    for(var i = 0; i < roleArray.length; i++) {
        var role = roleArray[i];

        var roleId = role.roleId;
        var roleName = role.roleName;

        //将roleName放入到模态框中
        $("#roleNameDiv").append(roleName + "</br>")

        window.roleIdArray.push(roleId);
    }
}

//执行分页，任何时候调用此函数都会重新加载页面
function generatePage() {
    //1、获取pageInfo
    var pageInfo = getPageInfoRemote();
    //2、填充页面表格
    fillTableBody(pageInfo);
}

//获取pageInfo数据
function getPageInfoRemote() {
    var ajaxResult = $.ajax({
        url: "role/get/role.json",
        data: {
            "keyword": window.keyword,
            "pageNum": window.pageNum,
            "pageSize": window.pageSize
        },
        type: "post",
        dataType: "json",
        async: false
    });

    console.log(ajaxResult);

    //判断当前响应状态码是否为200
    if (ajaxResult.status != 200) {
        layer.msg("失败！响应状态码=" + ajaxResult.status + " 说明信息=" + ajaxResult.statusText);
        return null;
    }

    //如果是200，则获取ResultEntity
    var resultEntity = ajaxResult.responseJSON;
    //从resultEntity中获取result
    var result = resultEntity.result;

    //判断result是否成功
    if (result == "FAILED") {
        layer.msg(resultEntity.message);
        return null;
    }
    //确认返回成功后，获取pageInfo
    var pageInfo = resultEntity.data;

    return pageInfo;
}

//填充页面表格
function fillTableBody(pageInfo) {
    // 清除 tbody 中的旧的内容
    $("#rolePageBody").empty();

    // 这里清空是为了让没有搜索结果时不显示页码导航条
    $("#Pagination").empty();

    //设置全选框未选中
    $("#sumarryChexbox").prop("checked", false);


    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查到您要的数据</td></tr>")
        return;
    }

    //使用pageInfo的list属性填充tbody
    for (var i = 0; i < pageInfo.list.length; i++) {
        var role = pageInfo.list[i];

        var roleId = role.id;
        var roleName = role.name;

        var numTd = "<td>" + (i + 1) + "</td>";
        var checkboxTd = "<td><input id="+ roleId +" class='itemBox' type=\"checkbox\"></td>";
        var roleNameTd = "<td>" + roleName + "</td>";

        var checkBtn = "<button type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>";
        var pencilBtn = "<button type=\"button\" id="+ roleId +" class=\"btn btn-primary btn-xs pencilBtn\"><i class=\" glyphicon glyphicon-pencil\"></i></button>";
        var removeBtn = "<button type=\"button\" id="+ roleId +" class=\"btn btn-danger btn-xs removeBtn\"><i class=\" glyphicon glyphicon-remove\"></i></button>";

        var btnTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";

        var tr = "<tr>" + numTd + checkboxTd + roleNameTd + btnTd + "</tr>";

        $("#rolePageBody").append(tr);

    }

    //生成分页导航条
    generateNavigator(pageInfo);
}

//生成分页页码导航条
function generateNavigator(pageInfo) {
    //总记录数
    var totalRecord = pageInfo.total;

    var properties = {
        num_edge_entries: 3,    //边缘条码数
        num_display_entries: 4, //主体条码数
        callback: pagintionCallback, //用户点击页码时调用这个函数实现页面跳转
        items_per_page: pageInfo.pageSize,   //每页显示多少条数据
        current_page: pageInfo.pageNum - 1,  //当前页
        prev_text: "上一页",   //上一页按钮上显示的文本
        next_text: "下一页"    //下一页按钮上显示的文本
    };

    //调用pagination函数
    $("#Pagination").pagination(totalRecord, properties);
}

// 翻页时的回调函数
function pagintionCallback(pageIndex, jQuery) {
    window.pageNum = pageIndex + 1;
    //调用分页函数
    generatePage();
    //取消页码超链接的行为
    return false;
}