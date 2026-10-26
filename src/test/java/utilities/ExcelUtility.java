package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    // number of data rows (header row is row 0, so this excludes it)
    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = wb.getSheet(sheetName);
            return sheet.getLastRowNum();
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = wb.getSheet(sheetName);
            Row row = sheet.getRow(rownum);
            return row.getLastCellNum();
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = wb.getSheet(sheetName);
            Row row = sheet.getRow(rownum);
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(row.getCell(colnum));   // blank cell returns ""
        }
    }
}