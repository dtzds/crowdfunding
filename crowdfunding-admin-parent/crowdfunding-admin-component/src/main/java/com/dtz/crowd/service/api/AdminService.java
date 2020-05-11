package com.dtz.crowd.service.api;

import com.dtz.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陶志胜
 * @createTime 2020/5/9
 */
public interface AdminService {

    void saveAdmin(Admin admin) throws Exception;

    List<Admin> getAll();

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
}
