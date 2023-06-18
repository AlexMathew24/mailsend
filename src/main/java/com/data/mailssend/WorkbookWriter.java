package com.data.mailssend;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class WorkbookWriter {
    public static  void saveWorkbook(Workbook workbook, String filePath)throws IOException {
        Path path=Path.of(filePath);
        try(FileOutputStream fos =new FileOutputStream(path.toFile())){
            workbook.write(fos);
        }
    }
}
