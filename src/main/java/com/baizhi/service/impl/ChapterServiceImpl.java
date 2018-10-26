package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;


import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.net.ssl.SSLEngine;
import java.io.File;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Created by no on 2018/10/25.
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private AlbumDao albumDao;

    @Override
    public void insert(MultipartFile fileName, File file, Chapter chapter)  {

        //先获取到文件的名字
        String originalFilename = fileName.getOriginalFilename();
        System.out.println(originalFilename+"or");
        //获取后缀名
        String extension = FilenameUtils.getExtension(originalFilename);
        //文件的新名字
        String newFile= UUID.randomUUID().toString()+"."+extension;
        System.out.println("文件的新名字"+newFile);



        /**
         * 计算音频的大小，并转换成mb
         */
        BigDecimal fileSize = new BigDecimal(fileName.getSize());

        String size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";


        chapter.setSize(size+"");
        chapter.setUrl("chapterUpload/"+newFile);
        try {
            fileName.transferTo(new File(file,newFile));

        } catch (IOException e) {
            e.printStackTrace();
        }



        /**
         * 计算音频的时长
         */

        File file1=new File(file+"/"+newFile);
        System.out.println(file1+"文件的绝对路劲++++");
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(file1);
            long ls = m.getDuration();
            System.out.println("时长"+ls/60000+"分"+(ls)/1000+"秒");
            chapter.setDuration(ls/60000+"分"+(ls)/1000+"秒");
        } catch (EncoderException e) {
            e.printStackTrace();
        }

        albumDao.update(chapter.getAdminFa().getId(),chapter.getAdminFa().getCount()+1);
        chapterDao.insert(chapter);


    }

}
