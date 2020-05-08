package com.dtz.crowd.service.impl;

import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.entity.AdminExample;
import com.dtz.crowd.exception.LoginFailedException;
import com.dtz.crowd.mapper.AdminMapper;
import com.dtz.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) throws Exception {
        adminMapper.insert(admin);
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String acct, String pswd) {
        //1、创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        //2、创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        //3、添加条件：按账号名查找
        criteria.andLoginAcctEqualTo(acct);
        //4、获取到按账号查找到的用户
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        //5、判断是否获取到用户
        if (admins == null || admins.size() == 0) {
            //6、如果为null,则抛出登录异常
            throw new LoginFailedException("用户不存在");
        }
        //7、如果获取到了用户，则比较其密码的密文是否一致
//        admins.stream().filter()
        
        return null;
    }
}
