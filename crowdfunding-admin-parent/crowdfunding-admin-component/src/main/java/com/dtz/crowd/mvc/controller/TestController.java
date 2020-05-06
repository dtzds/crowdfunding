package com.dtz.crowd.mvc.controller;

import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.service.api.AdminService;
import com.dtz.crowd.util.CrowdUtil;
import com.dtz.crowd.util.ResultEntity;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class TestController {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestController.class);


    @ResponseBody
    @RequestMapping("/test/sendComposeData/object.json")
    public ResultEntity<Student> sendData(@RequestBody Student student, HttpServletRequest request) {
        String a = null;
        System.out.println(a.length());

        logger.info(student.toString());
        return ResultEntity.successWithData(student);
    }

    @ResponseBody
    @RequestMapping("/test/sendComposeData/three.html")
    public String sendComposeData(@RequestBody Student student) {
        logger.info(student.toString());
        return "success";
    }

    @RequestMapping("/test/sendArray/two.html")
    @ResponseBody
    public String sendArray2(@RequestBody List<Integer> array) {
        array.stream().forEach(i-> System.out.println(i));

        return "success";
    }

    @RequestMapping("/test/sendArray/one.html")
    public String sendArray(@RequestParam("array[]") List<Integer> array) {
        return "target";
    }

    @RequestMapping("/test/ssm.html")
    public String test(ModelMap modelMap, HttpServletRequest request) {

        String a = null;
        System.out.println(a.length());

        List<Admin> all = adminService.getAll();
        modelMap.addAttribute("adminList", all);
        return "target";
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    static class Student {
        private Integer stuId;
        private String stuName;
        private Integer stuAge;
        private Address address;
        private List<Subject> subjectList;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    static class Address {
        private String province;
        private String city;
        private String street;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    static class Subject {
        private String name;
        private String score;
    }

}
