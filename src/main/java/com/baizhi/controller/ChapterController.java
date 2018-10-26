package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;

import it.sauronsoftware.jave.EncoderException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by no on 2018/10/25.
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/insert")
    public void insert(MultipartFile fileName, Chapter chapter, HttpServletRequest request) throws EncoderException {
        String realPath = request.getSession().getServletContext().getRealPath("/");

        File file=new File(realPath+"/chapterUpload");
        if(!file.exists()){
            file.mkdir();
        }
        chapterService.insert(fileName,file,chapter);

    }

    @RequestMapping("/downloadChapter")
    public void downloadChapter(String url, HttpServletRequest request, HttpServletResponse response){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file=new File(realPath+"/"+url);
        System.out.println(file+"---aaaaaaaaaaaaaaaaa");
       // String fileName=url.substring(url.indexOf("-")+1);
        try {
            response.setHeader("content-disposition", "attachment;url="+URLEncoder.encode(url,"UTF-8"));
            File file1=new File("f:/a/"+url);
            FileUtils.copyFile(file,response.getOutputStream());
           // FileUtils.copyFile(file,file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
