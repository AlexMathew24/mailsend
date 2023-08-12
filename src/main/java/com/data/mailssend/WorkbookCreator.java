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
        System.out.println("1---1");
        return workbook;

    }
    //------------change ||sql dependency

    private static void createHederRow(Sheet sheet){
        Row headerRow=sheet.createRow(0);
        String[] header={"userID","Name","lob flag"};
        for(int i=0;i<header.length;i++){
            Cell cell=headerRow.createCell(i);
            cell.setCellValue(header[i]);
            System.out.println(i);
        }
    }

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
