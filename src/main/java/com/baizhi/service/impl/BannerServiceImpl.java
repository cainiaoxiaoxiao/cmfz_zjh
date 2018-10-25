package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by no on 2018/10/24.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public Map BannerAll(int page,int rows) {

        Map map =new HashMap();

        /**
         * 计算从什么开始
         * 到什么结束
         */
        int start=(page-1)*rows;
        int end =rows;

        List<Banner> list = bannerDao.queryAll(start, end);
        int count = bannerDao.countAll();
        map.put("rows",list);
        map.put("total",count);
        return map;
    }

    @Override
    public void delete(int id) {
        bannerDao.deleteId(id);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }
}
