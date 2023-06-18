package com.data.mailssend;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class WorkbookCreator {
    public  static Workbook createWorkbook(List<Data> dataList){
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        createHederRow(sheet);
        populateDataRows(sheet,dataList);

        return workbook;

    }

    private static void createHederRow(Sheet sheet){
        Row headerRow=sheet.createRow(0);
        String[] header={"ID","Name","descrption"};
        for(int i=0;i<header.length;i++){
            Cell cell=headerRow.createCell(i);
            cell.setCellValue(header[i]);
        }
    }
//    private  static  void populateDataRows(Sheet sheet,List<MailssendApplication.Data> dataList){
//        for(int i=0;i<dataList.size();i++){
//            MailssendApplication.Data data =dataList.get(i);
//            Row dataRow=sheet.createRow(i+1);
//
//            Cell idCell= dataRow.createCell(0);
//            idCell.setCellValue(data.getId());
//
//            Cell nameCell= dataRow.createCell(1);
//            nameCell.setCellValue(data.getName());
//
//            Cell descriptionCell= dataRow.createCell(2);
//            descriptionCell.setCellValue(data.getDescrption());
//
//
//        }
private  static  void populateDataRows(Sheet sheet,List<Data> dataList) {
      int rowIndex=1;
      for(Data data: dataList){
          Row dataRow=sheet.createRow(rowIndex++);
          dataRow.createCell(0).setCellValue(data.getId());
          dataRow.createCell(1).setCellValue(data.getName());
          dataRow.createCell(2).setCellValue(data.getDescription());
      }

    }
}
