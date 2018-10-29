import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by no on 2018/10/28.
 */
public class TestPOI {

    @Test
    public void export() throws IOException {

        //创建excl对象
        Workbook workbook = new HSSFWorkbook();

        //创建excl工作空间
        Sheet sheet = workbook.createSheet("user");

        sheet.setColumnWidth(0,22*256);
        //根据工作空间创建行，下标是从0开始
        Row row = sheet.createRow(0);


        //根据行创建单元格，下标是从0开始
        Cell cell = row.createCell(0);

        //给单元格设值
        cell.setCellValue("这个是一个单元格");

        workbook.write(new FileOutputStream(new File("F:\\a.xls")));

    }
}
