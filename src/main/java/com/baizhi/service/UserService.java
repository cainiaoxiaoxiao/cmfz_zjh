package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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


}
