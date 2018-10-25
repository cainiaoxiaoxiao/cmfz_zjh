package com.baizhi.dao;

import com.baizhi.entity.Admin;

/**
 * Created by no on 2018/10/23.
 */
public interface AdminDao {

    /**
     * 根据账号和密码查询数据
     */
    public Admin queryOne(Admin admin);
}
