package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public static String[][] getData() throws IOException {

        String path = "./testData/LoginData.xlsx";
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        String[][] logindata = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {          // row 0 is the header, so start at 1
            for (int j = 0; j < totalcols; j++) {
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        return logindata;
    }
}