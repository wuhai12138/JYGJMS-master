package com.summ.utils;

import com.summ.model.response.CustomerStatmentRes;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.RichTextString;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by summ on 17/12/5.
 */

public class ExcelUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String ExportCustomerStatment(List<CustomerStatmentRes> list){

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("客户对账单");
        sheet.setDefaultColumnWidth(15);
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("日期");
        row.createCell(2).setCellValue("类型");
        row.createCell(3).setCellValue("金额");
        row.createCell(4).setCellValue("充值方式");
        row.createCell(5).setCellValue("操作人");
        row.createCell(6).setCellValue("终端");
        row.createCell(7).setCellValue("状态");
        row.createCell(8).setCellValue("余额");
        row.createCell(9).setCellValue("流水号");


        for (int i = 0; i < list.size(); i++){
            HSSFRow hssfRow = sheet.createRow(i+1);

            hssfRow.createCell(0).setCellValue(list.get(i).getStatmentCustomer());
            hssfRow.createCell(1).setCellValue(simpleDateFormat.format(list.get(i).getChargeDate()));
            hssfRow.createCell(2).setCellValue(list.get(i).getStatmentCustomerType());
            hssfRow.createCell(3).setCellValue((RichTextString) list.get(i).getChargeMoney());
            hssfRow.createCell(4).setCellValue(list.get(i).getChargeWayInfo());
            hssfRow.createCell(5).setCellValue(list.get(i).getAdminName());
            hssfRow.createCell(6).setCellValue(list.get(i).getTerminalInfo());
            hssfRow.createCell(7).setCellValue(list.get(i).getStatusInfo());
            hssfRow.createCell(8).setCellValue(list.get(i).getBalance().toString());
            hssfRow.createCell(9).setCellValue(list.get(i).getSerialNumber());
        }

        String excelName = System.currentTimeMillis() + ".xls";
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("/opt/excel/" + excelName);
//            output = new FileOutputStream("C:\\Users\\summ\\Desktop\\excel\\" + excelName);
            workbook.write(output);
            output.flush();
            return "http://www.jyguanjia.com/excel/" + excelName;
        } catch (Exception e) {
            e.printStackTrace();
            return "导出失败！: " + e.getMessage();
        }

    }

}
