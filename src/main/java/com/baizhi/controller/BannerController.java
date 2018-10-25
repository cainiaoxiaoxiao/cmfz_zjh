package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by no on 2018/10/24.
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/bannerALl")
    public Map bannerAll(int page,int rows){

        System.out.println("轮播图查询进来了");

        Map map = bannerService.BannerAll(page, rows);
        return map;
    }

    @RequestMapping("/delete")
    public  Boolean delete(int id){
        System.out.println("删除轮播图来客");
        try {
            bannerService.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/update")
    public void update(Banner banner){

        System.out.println("修改接收的数据"+banner);
        bannerService.update(banner);
    }

    @RequestMapping("/insert")
    public  Boolean insert(Banner banner, MultipartFile fileName) throws IOException {

        System.out.println("添加是数据需要接收的数据---->"+banner);
        System.out.println(fileName+"上传的文件");

        //得到文件老的名字
        String oldName=fileName.getOriginalFilename();
        System.out.println("oldName-------------"+oldName);

        //得到文件的新名字
        String newName= UUID.randomUUID().toString()+"_"+oldName;
        System.out.println("新文件的名字"+newName);

        //创建file文件
        File file=new File("G:\\idea\\maven\\finally\\cmfz_zjh\\src\\main\\webapp\\img\\"+newName);

        //写入到磁盘里面
        fileName.transferTo(file);
        banner.setUrl("img/" + newName);
        try {

            bannerService.insert(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
