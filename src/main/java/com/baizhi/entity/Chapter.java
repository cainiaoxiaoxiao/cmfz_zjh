package com.baizhi.entity;

/**
 * Created by no on 2018/10/25.
 */
public class Chapter {

    private String id;

    private String name;

    private  String  url;

    private String size;

    private String duration;//音频时长

    private  Album adminFa;//父表

    public Chapter() {
    }

    public Chapter(String id, String name, String url, String size, String duration) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.size = size;
        this.duration = duration;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Album getAdminFa() {
        return adminFa;
    }

    public void setAdminFa(Album adminFa) {
        this.adminFa = adminFa;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", size='" + size + '\'' +
                ", duration='" + duration + '\'' +
                ", adminFa=" + adminFa +
                '}';
    }
}
