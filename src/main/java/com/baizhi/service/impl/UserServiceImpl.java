package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserLocationDTO;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by no on 2018/10/28.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 到出文件
     *
     * @param response
     */
    @Override
    public void exportUser(HttpServletResponse response) {

        //查询全部的users
        List<User> users = userDao.queryAll();
        //设值头部信息
        List<String> tatle = Arrays.asList("编号", "法号", "名字", "性别", "省份", "城市", "手机号", "注册时间");
        List<String> tatle1 = Arrays.asList("id", "dhamaName", "name", "sex", "province", "city", "phoneNum", "regDate");

        //创建excl表格
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建工作空间
        HSSFSheet sheet = workbook.createSheet("user");

        //创建样式对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //剧中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //创建字体对象
        HSSFFont font = workbook.createFont();
        cellStyle.setFont(font);
        //设置字体样式
        font.setColor(Font.COLOR_RED);

        //设置时间对象
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);

        //创建一行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < tatle.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(tatle.get(i));
        }

        //每一列的数据
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Class<? extends User> aClass = users.get(i).getClass();
            for (int j = 0; j < tatle1.size(); j++) {
                String s = "get" + tatle1.get(j).substring(0, 1).toUpperCase() + tatle1.get(j).substring(1);
                try {
                    Object invoke = aClass.getDeclaredMethod(s, null).invoke(users.get(i), null);
                    if (invoke instanceof Date) {
                        //创建一个单元格
                        HSSFCell cell = row1.createCell(j);
                        cell.setCellStyle(cellStyle1);
                        cell.setCellValue((Date) invoke);
                        sheet.setColumnWidth(j, 22 * 256);
                    } else if (invoke instanceof Integer) {
                        //创建一个单元格
                        HSSFCell cell = row1.createCell(j);
                        cell.setCellValue((Integer) invoke);
                    } else {
                        HSSFCell cell = row1.createCell(j);
                        cell.setCellStyle(cellStyle1);
                        sheet.setColumnWidth(j, 22 * 256);
                        cell.setCellValue((String.valueOf(invoke)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

        //响应给浏览器
        long time = new Date().getTime();
        String s = time + "文件.xls";
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    /**
     * 导入
     *
     * @param fileName
     */
    @Override
    public void importUser(MultipartFile fileName) {
        //创建一个list集合
        List<User> list = new ArrayList();


        //通过MultipartFile得到一个输入流
        Workbook workbook = null;
        try {
            InputStream inputStream = fileName.getInputStream();

            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建工作空间
        Sheet sheet = workbook.getSheet("user");

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            //创建行
            Row row = sheet.getRow(i);
            //获取第一行的数据

            int id = (int) row.getCell(0).getNumericCellValue();//得到id
            String dhamaName = row.getCell(1).getStringCellValue();
            String name = row.getCell(2).getStringCellValue();
            String sex = row.getCell(3).getStringCellValue();
            String province = row.getCell(4).getStringCellValue();
            String city = row.getCell(5).getStringCellValue();
            String phoneNum = row.getCell(6).getStringCellValue();
            Date regDate = row.getCell(7).getDateCellValue();

            User user = new User();
            user.setCity(city);
            user.setDhamaName(dhamaName);
            user.setId(id);
            user.setSex(sex);
            user.setPhoneNum(phoneNum);
            user.setRegDate(regDate);
            user.setName(name);
            user.setProvince(province);
            list.add(user);
        }
        userDao.insert(list);
    }

    /**
     * 自定义导出
     *
     * @param response
     * @param titles
     * @param fileds
     */
    @Override
    public void customerExport(HttpServletResponse response, String titles, String fileds) {
        List<User> users = userDao.queryAll();


        String[] filedArray = fileds.split(",");
        String[] titleArray = titles.split(",");
        Workbook workbook = new HSSFWorkbook();

        CellStyle cellStyle1 = workbook.createCellStyle();
        //设置日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年mm月dd天");
        cellStyle1.setDataFormat(format);

        Sheet sheet = workbook.createSheet("user");
        Row row = sheet.createRow(0);
        for (
                int i = 0;
                i < titleArray.length; i++)

        {
            row.createCell(i).setCellValue(titleArray[i]);
        }

        for (
                int i = 0; i < users.size(); i++)

        {
            Row row1 = sheet.createRow(i + 1);
            Class<? extends User> aClass = users.get(i).getClass();
            for (int j = 0; j < filedArray.length; j++) {
                //getId
                String methodName = "get" + filedArray[j].substring(0, 1).toUpperCase() + filedArray[j].substring(1);
                try {
                    Object invoke = aClass.getDeclaredMethod(methodName, null).invoke(users.get(i), null);
                    if (invoke instanceof Date) {
                        Cell cell = row1.createCell(j);
                        cell.setCellStyle(cellStyle1);
                        cell.setCellValue((Date) invoke);
                        sheet.setColumnWidth(j, 22 * 256);
                    } else if (invoke instanceof Integer) {
                        Cell cell = row1.createCell(j);
                        cell.setCellValue((Integer) invoke);
                    } else {
                        Cell cell = row1.createCell(j);
                        cell.setCellValue(String.valueOf(invoke));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        long time = new Date().getTime();
        String s = time + "文件.xls";
        try

        {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
        } catch (
                UnsupportedEncodingException e)

        {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        try

        {
            workbook.write(response.getOutputStream());
        } catch (
                IOException e)

        {
            e.printStackTrace();

        }
    }

    @Override
    public Map statisticsDate() {

        List<Integer> list = userDao.queryDate();
        Map map = new HashMap();
        map.put("intervals",new String[]{"7天","15天","21天"});
        map.put("counts",list);
        return map;
    }

    @Override
    public List<UserLocationDTO> queryLocatNan() {

        return  userDao.queryLocatNan();

    }

    @Override
    public List<UserLocationDTO> queryLocatNv() {

        return  userDao.queryLocatNv();
    }

    @Override
    public List<Map> queryLocat(String sex) {

        List list1=new ArrayList();
        List<UserLocationDTO> list = userDao.queryLocat(sex);
        for (UserLocationDTO userLocationDTO : list) {
            Map map =new HashMap();
            String province = userLocationDTO.getName();
            int count = userLocationDTO.getValue();
            map.put("name",province);
            map.put("value",count);
            list1.add(map);

        }
        return list1 ;
    }

}
