package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by no on 2018/10/25.
 */
public interface ChapterService {

    public void insert(MultipartFile fileName, File file, Chapter chapter);

}
