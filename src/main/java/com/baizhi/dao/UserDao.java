package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by no on 2018/10/28.
 */
public interface UserDao {


    public List<User> queryAll();

    /**
     * 添加数据
     */
    public void insert(@Param("list")List<User> list);
}
