package com.baizhi.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by no on 2018/10/25.
 */
public class Album {



    private int id;//

    private String name;

    private  String coverImg;//封面图片

    private int count;//专辑数量

    private double score;//专辑评分

    private String author;

    private  String broadCast;

    private  String brief;//专辑简介



    private  List<Chapter> children;

    public Album() {
    }

    public Album(int id, String name, String coverImg, int count, double score, String author, String broadCast, String brief) {

        this.id = id;
        this.name = name;
        this.coverImg = coverImg;
        this.count = count;
        this.score = score;
        this.author = author;
        this.broadCast = broadCast;
        this.brief = brief;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadCast() {
        return broadCast;
    }

    public void setBroadCast(String broadCast) {
        this.broadCast = broadCast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public List<Chapter> getChildren() {
        return children;
    }

    public void setChildren(List<Chapter> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AlbumDao{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", count=" + count +
                ", score=" + score +
                ", brief='" + brief + '\'' +
                ", children=" + children +
                '}';
    }
}
