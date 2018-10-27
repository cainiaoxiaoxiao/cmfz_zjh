package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map queryAll(int page, int rows) {
        Map map =new HashMap();

        int start=(page-1)*rows;
        int end=rows;

        List<Album> list = albumDao.queryAll(start,end);
        int total = albumDao.countTo();

        map.put("rows",list);
        map.put("total",total);
        return map;
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
