package com.baizhi.controller;

import com.baizhi.entity.UserLocationDTO;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/statisticsDate")
    public Map statisticsDate(){
        return userService.statisticsDate();
    }

    @RequestMapping("/queryLocatNan")
    public List queryLocatNan(){
        List maps = userService.queryLocatNan();
        System.out.println("地图"+maps);
        return maps;
    }
    @RequestMapping("/queryLocatNv")
    public List queryLocatNv(){
        List maps = userService.queryLocatNv();
        System.out.println("地图"+maps);
        return maps;
    }
  /*@RequestMapping("/queryLocat")
  public List queryLocat(String sex){
      List list = userService.queryLocat(sex);
      System.out.println("地图"+list);
      return list;
  }*/
}
