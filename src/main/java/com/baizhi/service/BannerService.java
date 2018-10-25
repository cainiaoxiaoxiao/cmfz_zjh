package com.baizhi.service;


import com.baizhi.entity.Banner;

import java.util.Map;

/**
 * Created by no on 2018/10/24.
 */
public interface BannerService {

    /**
     * 查询轮播图全部
     */
    public Map BannerAll(int page, int rows);

    /**
     * 根据id删除数据
     */
    public void delete(int id);

    /**
     * 修改数据
     */
    public void update(Banner banner );

    /**
     * 添加数据
     */
    public void insert(Banner banner);
}
