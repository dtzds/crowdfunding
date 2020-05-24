package com.dtz.crowd.service.api;


import com.dtz.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    /**
     * 获取角色并封装到pageInfo中
     * @param keyword 检索关键字
     * @param pageNum 页码
     * @param pageSize 每页显示数据数目
     * @return
     */
    PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 添加角色
     * @param role 角色
     */
    void saveRole(Role role);

    /**
     * 修改角色名
     * @param role
     */
    void updateRole(Role role);

    /**
     * 根据id删除角色
     * @param ids
     */
    void removeRoleByIds(List<Integer> ids);
}
