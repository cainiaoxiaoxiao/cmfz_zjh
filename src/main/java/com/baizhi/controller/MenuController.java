package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by no on 2018/10/23.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryAll")
    public @ResponseBody List queruAll(){
        System.out.println("进来查找菜单了");
        List<Menu> list = menuService.queryAll();
       // map.put("list",list);
        return list;
    }

    @RequestMapping("queryById")
    public List queryById(int id){
        System.out.println("进来查找二级菜单了");
        return menuService.queryById(id);
    }
}
