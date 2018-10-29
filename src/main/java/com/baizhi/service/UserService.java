package com.baizhi.service;

import com.baizhi.entity.User;

import com.baizhi.entity.UserLocationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by no on 2018/10/28.
 */
public interface UserService {

    //导出
    public void exportUser(HttpServletResponse response);

    public List<User> queryAll();

    //导入
    public void importUser(MultipartFile fileName);


    public void customerExport(HttpServletResponse response,String titles, String fileds);


    //统计用户的注册时间
    public Map statisticsDate();

    /**
     * 根据用户的所在地查询用户的人数
     */
    public List<UserLocationDTO> queryLocatNan();
    public List<UserLocationDTO> queryLocatNv();

    public List<Map> queryLocat(@Param("sex")String sex);
}
