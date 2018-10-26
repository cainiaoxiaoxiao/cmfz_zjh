package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by no on 2018/10/25.
 */
public interface AlbumDao {

    /**
     * 查询所有专辑和专辑下的音频
     */
    public List<Album> queryAll();

    /**
     * 添加专辑
     */
    public void insert(Album album);

    /**
     * 修改数量
     */
    public void update(@Param("id") int id, @Param("count") int count);
}
