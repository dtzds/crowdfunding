package com.dtz.crowd.service.impl;

import com.dtz.crowd.constant.CrowdConstant;
import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.entity.AdminExample;
import com.dtz.crowd.exception.LoginAcctAlreadyInUseException;
import com.dtz.crowd.exception.LoginFailedException;
import com.dtz.crowd.mapper.AdminMapper;
import com.dtz.crowd.service.api.AdminService;
import com.dtz.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    public void saveAdmin(Admin admin){
        //1、对密码进行加密
        String userPswd = admin.getUserPswd();
        userPswd = CrowdUtil.md5(userPswd);
        admin.setUserPswd(userPswd);

        //2、新增一个创建时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        //3、添加到数据库中
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getClass().getName());
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
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
        //4、获取到按账号查找到的用户admin
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        //5、判断是否获取到用户(为空）
        if (admins == null || admins.size() == 0) {
            //6、如果为null,则抛出登录异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //7、如果获取到的用户不唯一，则抛出不唯一的异常
        if (admins.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = admins.get(0);

        //8、如果获取到的用户为null,则抛出异常
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //9、对密文进行比较
        if (!Objects.equals(admin.getUserPswd(), CrowdUtil.md5(pswd))) {
            //10、如果不一致，则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //11、一致，则返回admin
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        //1、调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        //2、执行查询
        List<Admin> admins = adminMapper.selectAdminByKeyword(keyword);

        //3、封装到PageInfo对象中
        return new PageInfo<>(admins);
    }

    @Override
    public void remove(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }
}
