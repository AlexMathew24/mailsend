package com.data.mailssend;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class WorkbookWriter {
    public static  void saveWorkbook(Workbook workbook, String filePath)throws IOException {

        File file= new File(filePath) ;
        File directory =file.getParentFile();
        if(directory!=null && !directory.exists()){
            directory.mkdir();
        }

        try(FileOutputStream outputStream=new FileOutputStream(file)){

            workbook.write(outputStream);
        }
    }
}
