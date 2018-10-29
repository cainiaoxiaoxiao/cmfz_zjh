package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserLocationDTO;
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

    /**
     * 查询用户注册时间进三周，和进一周，进二周的人数
     */
    public List<Integer> queryDate();

    /**
     * 根据用户的所在地查询用户的人数
     */
    public List<UserLocationDTO> queryLocatNan();
    public List<UserLocationDTO> queryLocatNv();

   public List<UserLocationDTO> queryLocat(@Param("sex")String sex);
}
