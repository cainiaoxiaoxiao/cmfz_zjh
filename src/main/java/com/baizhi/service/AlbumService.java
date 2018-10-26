package com.baizhi.service;


import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;

/**
 * Created by no on 2018/10/25.
 */

public interface AlbumService {

    public List<Album> queryAll();

    public void insert(Album album,MultipartFile multipartFile,File file);
}
