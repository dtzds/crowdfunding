package com.dtz.crowd.mvc.controller;

import com.dtz.crowd.entity.Role;
import com.dtz.crowd.mapper.RoleMapper;
import com.dtz.crowd.service.api.RoleService;
import com.dtz.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/role/roleIdArray/remove.json")
    public ResultEntity removeRoleByIds(@RequestBody List<Integer> roleIdArray) {
        roleService.removeRoleByIds(roleIdArray);
        return ResultEntity.successWithoutData();
    }

    /**
     * 修改角色名
     * @param role 传入角色id及要修改的角色名
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity updateRole(Role role) {
        roleService.updateRole(role);

        return ResultEntity.successWithoutData();
    }

    /**
     * 新增角色
     * @param role 角色，只传入name(角色名）
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/add.json")
    public ResultEntity addRole(Role role) {
        roleService.saveRole(role);

        return ResultEntity.successWithoutData();
    }

    /**
     * 获取角色数据
     * @param keyword 检索关键字
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/get/role.json")
    public ResultEntity<PageInfo<Role>> getRoles(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);
        return ResultEntity.successWithData(pageInfo);
    }
}
