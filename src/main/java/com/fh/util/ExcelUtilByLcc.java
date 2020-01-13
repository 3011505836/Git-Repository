package com.fh.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExcelUtilByLcc {

    public static XSSFWorkbook generateWorkbook (List dataList, Class cls, String[] headerNameArr, String[] fieldNameArr, String sheetName){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        return workbook;
    }
}
