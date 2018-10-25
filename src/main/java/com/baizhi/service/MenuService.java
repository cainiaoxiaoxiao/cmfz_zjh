package com.baizhi.service;

import com.baizhi.entity.Menu;

import java.util.List;

/**
 * Created by no on 2018/10/23.
 */
public interface MenuService {
    /**
     * 查询全部
     */
    public List<Menu> queryAll();

    /**
     * 根据父类id查询
     */
    public List<Menu> queryById(int id);
}
