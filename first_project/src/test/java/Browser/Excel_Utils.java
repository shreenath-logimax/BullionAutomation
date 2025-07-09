package Browser;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Excel_Utils {

    static final String FILE_PATH = "D:\\Documents\\java_automation.xlsx\\";
    
    public static String saveWorkbook(String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
        		XSSFWorkbook workbook = new XSSFWorkbook()){
            workbook.write(fos);
            workbook.close();
            String save = ("Workbook saved to: " + filePath);
            
            System.out.println(save);
            return save;
        }
    }
    
    // Get functions to execute from "Master" sheet
    public static List<String> getMasterSheetData(String filePath) throws IOException {
        List<String> functionsToExecute = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
        		
        		XSSFWorkbook workbook = new XSSFWorkbook(fis)){
                
            Sheet sheet = workbook.getSheet("Master");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Cell execCell = row.getCell(1); // "Execution"
                if (execCell != null && Pattern.compile("yes", Pattern.CASE_INSENSITIVE)
                        .matcher(getCellValue(execCell)).find()) {
                    Cell funcCell = row.getCell(0); // "Function"
                    functionsToExecute.add(getCellValue(funcCell));
                }
            }
        }
        return functionsToExecute;
    }
    
    // Get number of valid rows (non-empty in column A)
    public static int getValidRows(String filePath, String sheetName) throws IOException {
        XSSFWorkbook workBookXlsx = new XSSFWorkbook(new FileInputStream(filePath));
				Sheet sheet = workBookXlsx.getSheet(sheetName);
				int totalRows = sheet.getLastRowNum();
				System.out.println(totalRows);
            return (totalRows); 
        }
  
    // Count Pass and Fail
    public static String getStatus(String filePath, String sheetName) throws IOException {
        int pass = 0, fail = 0;
        try (FileInputStream fis = new FileInputStream(filePath);
        		XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            int count = getValidRows(filePath, sheetName);

            for (int i = 1; i < count; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Cell statusCell = row.getCell(1);
                String val = getCellValue(statusCell).toLowerCase();
                if ("pass".equals(val)) pass++;
                else if ("fail".equals(val)) fail++;
            }
        }
        return "Pass " + pass + ", Fail " + fail;
    }

    // Update status in "Master" sheet
    public static void updateMasterStatus(String filePath, String status, String functionName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Master");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            Cell functionCell = row.getCell(0);
            if (getCellValue(functionCell).equals(functionName)) {
                Cell statusCell = row.createCell(2); // Column C
                statusCell.setCellValue(status);
                break;
            }
        }

        fis.close(); // close input stream before writing
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }

 // Utility class

    public static String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }


public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
	 FileInputStream fis = new FileInputStream(filePath);
	 XSSFWorkbook workbook = new XSSFWorkbook(fis);
	 Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        if (cell == null) { 
        	return "";
        	}
        else
        {
        	String cellvalue=cell.toString();
        return cellvalue;
        }   
}

public static void setCellData(String filePath, String sheetName, int rowNum, int colNum, String value) throws IOException {
    FileInputStream fis = new FileInputStream(filePath);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    Sheet sheet = workbook.getSheet(sheetName);
    Row row = sheet.getRow(rowNum);
    if (row == null)row = sheet.createRow(rowNum);
    Cell cell = row.getCell(colNum);
    if (cell == null) cell = row.createCell(colNum);
    cell.setCellValue(value);
    fis.close();

    FileOutputStream fos = new FileOutputStream(filePath);
    workbook.write(fos);
    workbook.close();
    fos.close();
}

// Get sheet names

public static List<String> getSheetNames(String filePath) throws IOException {
	XSSFWorkbook workBookXlsx = new XSSFWorkbook(new FileInputStream(filePath));
	ArrayList<String> sheetNames = new ArrayList<>();
	
	int numberOfSheets = workBookXlsx.getNumberOfSheets();
	
	for (int i = 0; i < numberOfSheets; i++) {
	    sheetNames.add(workBookXlsx.getSheetAt(i).getSheetName());
	}
	
	workBookXlsx = null;
	return sheetNames;
	}
//=====================================================================================================================================================
/*
 * public static List<Map<String, String>> readExcel(String filePath) throws
 * IOException { List<Map<String, String>> data = new ArrayList<>(); try
 * (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook
 * workbook = new XSSFWorkbook(fis)) { Sheet sheet = workbook.getSheetAt(0); //
 * //default to first sheet Row headerRow = sheet.getRow(0);
 * 
 * for (int i = 1; i <= sheet.getLastRowNum(); i++) { Map<String, String>rowData
 * = new HashMap<>(); Row row = sheet.getRow(i); if (row == null) continue;
 * 
 * for (int j = 0; j < headerRow.getLastCellNum(); j++) { Cell header =
 * headerRow.getCell(j); Cell cell = row.getCell(j);
 * rowData.put(header.getStringCellValue(), getCellValue(cell)); }
 * data.add(rowData); System.out.println(rowData); } } return data; }
 */
}