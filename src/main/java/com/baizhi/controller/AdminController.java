package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * Created by no on 2018/10/23.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, String enCode, HttpSession session){
        System.out.println("进来了");
        System.out.println(enCode+"输入的验证码-----------------------");
        System.out.println(admin+"输入的对象------------");

        String code = (String) session.getAttribute("vrifyCode");
        System.out.println(code+"获得的的验证码------");
        Admin login = adminService.login(admin);
        System.out.println(login+"查询的用户------");
        if(code.equals(enCode)){
            if(login!=null){
                return "redirect:/menu/queryAll";
            }else{
                return "login";
            }
        }

        return "login";
    }
}
