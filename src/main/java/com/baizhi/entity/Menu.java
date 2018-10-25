package com.baizhi.entity;

import java.util.List;

/**
 * Created by no on 2018/10/23.
 */
public class Menu {

    private int id;

    private String title;

    private int parent_id;

    private String iconCis;

    private String url;

    private List<Menu> menuList;

    public Menu() {
    }

    public Menu(int id, String title, int parent_id, String iconCis, String url) {
        this.id = id;
        this.title = title;
        this.parent_id = parent_id;
        this.iconCis = iconCis;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getIconCis() {
        return iconCis;
    }

    public void setIconCis(String iconCis) {
        this.iconCis = iconCis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", parent_id=" + parent_id +
                ", iconCis='" + iconCis + '\'' +
                ", url='" + url + '\'' +
                ", menuList=" + menuList +
                '}';
    }
}
