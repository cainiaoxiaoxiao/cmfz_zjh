package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * Created by no on 2018/10/25.
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;
    
    @RequestMapping("queryAll")
    public Map queryAll(int page, int rows){

        return albumService.queryAll(page,rows);
    }

    @RequestMapping("insert")
    public Boolean insert(MultipartFile fileName, Album album, HttpServletRequest request){


        try {
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File file=new File(realPath+"/albumUpload");
            if(!file.exists()){
                file.mkdir();
            }
            albumService.insert(album,fileName,file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
