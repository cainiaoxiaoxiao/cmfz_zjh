package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by no on 2018/10/28.
 */
public class User {

    private Integer id;

    private String headPic;//头像

    private String dhamaName;//法号

    private String name;//名字

    private String sex;//性别

    private String province;//省份

    private String city;//城市

    private String sign;

    private String phoneNum;//手机号

    private String salt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regDate;//注册时间

    public User() {
    }

    public User(Integer id, String headPic, String dhamaName, String name, String sex, String province, String city, String sign, String phoneNum, String salt, Date regDate) {
        this.id = id;
        this.headPic = headPic;
        this.dhamaName = dhamaName;
        this.name = name;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.sign = sign;
        this.phoneNum = phoneNum;
        this.salt = salt;
        this.regDate = regDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getDhamaName() {
        return dhamaName;
    }

    public void setDhamaName(String dhamaName) {
        this.dhamaName = dhamaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", headPic='" + headPic + '\'' +
                ", dhamaName='" + dhamaName + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", salt='" + salt + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
