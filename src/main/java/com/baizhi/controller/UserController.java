package com.baizhi.controller;

import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by no on 2018/10/28.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/exportUser")
    public void exportUser(HttpServletResponse response){
        userService.exportUser(response);
    }

    @RequestMapping("/queryAll")
    public List queryAll(){
        return userService.queryAll();
    }

    @RequestMapping("/importUser")
    public void importUser(MultipartFile excel) {
            userService.importUser(excel);
    }

    @RequestMapping("customerExport")
    public void customerExport(HttpServletResponse response,String titles, String fileds){
        userService.customerExport(response,titles,fileds);
    }
}
