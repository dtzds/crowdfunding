<%--
  Created by IntelliJ IDEA.
  User: 陶志胜
  Date: 2020/5/24
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="editRoleModal" class="modal fade" tabindex="-1" role="dialog">
    <div  class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">角色修改</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group has-success has-feedback">
                        <input type="text" name="roleName" class="form-control" id="inputSuccess4" autofocus>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="editRoleBtn" type="button" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i> 修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
