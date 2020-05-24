package com.dtz.crowd.service.impl;

import com.dtz.crowd.entity.Role;
import com.dtz.crowd.entity.RoleExample;
import com.dtz.crowd.mapper.RoleMapper;
import com.dtz.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        //1、调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        //2、执行查询
        List<Role> roles = roleMapper.selectPageInfoWithKeyword(keyword);

        return new PageInfo<>(roles);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void removeRoleByIds(List<Integer> ids) {
        RoleExample roleExample = new RoleExample();

        RoleExample.Criteria criteria = roleExample.createCriteria();

        criteria.andIdIn(ids);

        roleMapper.deleteByExample(roleExample);
    }

}
