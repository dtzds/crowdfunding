package com.dtz.crowd.mvc.controller;

import com.dtz.crowd.constant.CrowdConstant;
import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.entity.Role;
import com.dtz.crowd.mapper.AdminMapper;
import com.dtz.crowd.service.api.AdminService;
import com.dtz.crowd.service.api.RoleService;
import com.dtz.crowd.util.CrowdUtil;
import com.dtz.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

/**
 * @author EWRT
 * @createTime 2020/5/8
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * 修改管理员信息
     * @param admin
     * @param pageNum
     * @param keyword
     * @return
     */
    @RequestMapping("/admin/do/edit.html")
    public String edit(Admin admin,
                       @RequestParam(value = "pageNum") Integer pageNum,
                       @RequestParam(value = "keyword") String keyword) {
        adminService.update(admin);

        return "redirect:/admin/get/page.html?pageNum="+ pageNum +"&keyword=" + keyword;
    }

    /**
     * 添加管理员，添加完成后进行回显
     * @param admin
     * @return
     */
    @RequestMapping("/admin/do/save.html")
    public String save(Admin admin) {

        logger.info(admin.toString());

        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    /**
     * 跳转到管理员信息编辑页面，需要显示该管理员信息
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/to/edit.html")
    public String toEditPage(@RequestParam("id") Integer id,
                             ModelMap modelMap) {
        //获取管理员信息
        Admin admin = adminService.getAdminById(id);

        //将管理员信息存入modelMap中
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_ADMIN, admin);

        return "admin-edit";
    }

    /**
     * 删除管理员
     * @param id
     * @param pageNum
     * @param keyword
     * @return
     */
    @RequestMapping("/admin/do/{adminId}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("adminId") Integer id,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyword") String keyword) {
        //执行删除操作
        adminService.remove(id);
        //重定向到/admin/
        return "redirect:/admin/get/page.html?pageNum="+ pageNum +"&keyword=" + keyword;
    }

    /**
     * 获取用户数据
     * @param keyword   用户查询关键字
     * @param pageNum   页码
     * @param pageSize  每页显示数目
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              ModelMap modelMap) {
        //1、调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);

        //2、将PageInfo存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "admin-page";
    }

    /**
     * 退出登陆
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/logout.html")
    public String logout(HttpSession session) {
        //强制session失效
        session.invalidate();

        return "redirect:/admin/to/login.html";
    }

    /**
     * 登陆
     * @param loginAcct
     * @param userPswd
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session) {
        //调用service方法执行登陆检查
        //这个方法如果能够返回admin对象说明登陆成功，如果账号密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);

        //将返回的admin对象存入Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        return "redirect:/admin/to/main.html";
    }



}
