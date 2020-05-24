package com.dtz.crowd.service.api;

import com.dtz.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陶志胜
 * @createTime 2020/5/9
 */
public interface AdminService {

    /**
     * 添加管理员
     * @param admin
     */
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    /**
     * 通过id获取管理员账号的信息
     * @param id
     * @return
     */
    Admin getAdminById(Integer id);

    /**
     * 管理员登陆
     * @param acct  管理员账号
     * @param pswd  管理员密码
     * @return
     */
    Admin getAdminByLoginAcct(String acct, String pswd);

    /**
     *
     * @param keyword 查询内容（关键字）
     * @param pageNum 页数
     * @param pageSize 每页显示个数
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据管理员id删除管理员
     * @param id
     */
    void remove(Integer id);

    /**
     * 更新管理员信息
     * @param admin
     */
    void update(Admin admin);
}
