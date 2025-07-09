package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;

public class Contract_Symbol {

	public static void Add_Contract_Symbol(WebDriver driver) {
		try {
            String sheetName = "Contract Symbol";
            String filePath = Excel_Utils.FILE_PATH;
            int totalRows = Excel_Utils.getValidRows(filePath, sheetName);

            for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
                List<String> rowData = new ArrayList<>();

                for (int col = 3; col < 26; col++) {
                    String cellValue = Excel_Utils.getCellData(filePath, sheetName, rowNum, col);
                    rowData.add(cellValue);
                }

                String[] data = rowData.toArray(new String[0]);
                

                // Call the form fill method
                fillRPanelCommodity(driver,
                    data[0],  data[1],data[2]
                    		);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
		

 public static void fillRPanelCommodity(WebDriver driver,
        String Contract_Symbol, 
        String Type,
        String Active) {
	 try {
 		 driver.findElement(By.xpath("//span[contains(text(),'Masters')]")).click();
         Thread.sleep(2000);
         driver.findElement(By.xpath("//span[contains(text(),'Contract Symbol')]")).click();
         Thread.sleep(2000);
         driver.findElement(By.xpath("//a[contains(text(),' Add')]")).click();
         Thread.sleep(2000);
         driver.findElement(By.id("contract_symbol")).sendKeys(Contract_Symbol);
         
         Thread.sleep(2000);
         WebElement Commodity_Type = driver.findElement(By.id("com_type"));
         Select Select_type = new Select(Commodity_Type);
         Select_type.selectByVisibleText(Type);
         Thread.sleep(2000);
         if(Active.equalsIgnoreCase("Yes")) {
         	driver.findElement(By.xpath("//label[@for='com_active_yes']")).click();
         }else {
         	driver.findElement(By.xpath("//label[@for='com_active_noo']")).click();
         }
         driver.findElement(By.xpath("//button[@type='submit']")).click();
         System.out.println("Contract Symbol successfully");
	 }
      catch (Exception e) {
             e.printStackTrace();
         }
    }
	 
 }
