package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by no on 2018/10/24.
 */
public interface BannerDao {

    /**
     * 查询所有
     */
    public List<Banner> queryAll(@Param("start")int start,@Param("end")int end);

    /**
     * 查询总数
     */
    public int countAll();

    /**
     * 根据id删除
     */
    public void deleteId(int id);

    /**
     * 根据id修改数据
     */
    public void update(Banner banner);

    /**
     * 添加数据
     */
    public void insert(Banner banner);
}
