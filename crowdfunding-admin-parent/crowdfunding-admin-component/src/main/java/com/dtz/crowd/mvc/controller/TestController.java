package com.dtz.crowd.mvc.controller;

import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.mapper.AdminMapper;
import com.dtz.crowd.service.api.AdminService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String test(ModelMap modelMap) {
        List<Admin> all = adminService.getAll();
        modelMap.addAttribute("adminList", all);
        return "target";
    }

}
