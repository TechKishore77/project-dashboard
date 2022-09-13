package qa.ade.project_dashboard.excel;

import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

// getSheetValues taken and modified from https://stackoverflow.com/a/28937651/4453794

public class ExcelReadHelper {
    public static Map<String, Object[][]> getSheetValues(File excelFile) {
        XSSFRow row;
        XSSFCell cell;
        Object[][] values = null;
        Map<String, Object[][]> sheetValuesMap = new HashMap<>();
        XSSFWorkbook workbook = null;
        try {
            FileInputStream inputStream = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(inputStream);

            // get sheet number
            int sheetCn = workbook.getNumberOfSheets();

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int cn = 0; cn < sheetCn; cn++) {

                // get 0th sheet data
                XSSFSheet sheet = workbook.getSheetAt(cn);

                // get number of rows from sheet
                int rows = sheet.getPhysicalNumberOfRows();

                // get number of cell from row
                int cells = sheet.getRow(cn).getPhysicalNumberOfCells();

                String name = sheet.getSheetName();

                values = new Object[rows][cells];

                for (int r = 0; r < rows; r++) {
                    row = sheet.getRow(r);
                    if (row != null) {
                        for (int c = 0; c < cells; c++) {
                            cell = row.getCell(c);

                            CellValue cellValue = evaluator.evaluate(cell);

                            if (cellValue != null) {

                                switch (cellValue.getCellType()) {
                                    case BOOLEAN:
                                        values[r][c] = cellValue.getBooleanValue();
                                        break;
                                    case NUMERIC:
                                        if (DateUtil.isCellDateFormatted(cell)) {
                                            values[r][c] = cell.getDateCellValue();
                                        } else {
                                            values[r][c] = cellValue.getNumberValue();
                                        }
                                        break;
                                    case STRING:
                                        values[r][c] = cellValue.getStringValue();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
                sheetValuesMap.put(name, values);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheetValuesMap;
    }
}
