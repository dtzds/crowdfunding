package com.dtz.crowd.service.impl;

import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.mapper.AdminMapper;
import com.dtz.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) throws Exception {
        adminMapper.insert(admin);
        throw new Exception("抛出异常");
    }
}