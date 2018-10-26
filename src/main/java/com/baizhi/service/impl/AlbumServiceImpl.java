package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.service.AdminService;
import com.baizhi.service.AlbumService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by no on 2018/10/25.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;



    @Override
    public List<Album> queryAll() {
        return albumDao.queryAll();
    }

    @Override
    public void insert(Album album, MultipartFile multipartFile, File file) {

        try {
          /*  System.out.println("文件的路劲"+file);
            System.out.println(multipartFile);
            String name = multipartFile.getName();

            //获取后缀名
            String originalFilename = multipartFile.getOriginalFilename();

            System.out.println(name);
            String  fileName=UUID.randomUUID().toString()+"-"+name+"."+originalFilename;

            System.out.println(fileName+"新文件名");*/

            String originalFilename = multipartFile.getOriginalFilename();
            String s = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(originalFilename);
            String newName = s +"."+ extension;

            album.setCoverImg("albumUpload/"+newName);
            multipartFile.transferTo(new File(file,newName));
            System.out.println("111"+album);
            albumDao.insert(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
