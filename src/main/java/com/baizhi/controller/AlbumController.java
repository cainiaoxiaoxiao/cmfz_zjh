package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by no on 2018/10/25.
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;
    
    @RequestMapping("queryAll")
    public List<Album> queryAll(){

        return albumService.queryAll();
    }

    @RequestMapping("insert")
    public void insert(MultipartFile fileName, Album album, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");

        File file=new File(realPath+"/albumUpload");
        if(!file.exists()){
            file.mkdir();
        }


        albumService.insert(album,fileName,file);
    }
}
