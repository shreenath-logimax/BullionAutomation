package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;


public class RPanel_Bank_Name{
	
    public static void commdity_RpanelBank(WebDriver driver) {
        try {
            String sheetName = "R Panel Bank Master";
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
                    data[0],  data[1],data[2],  data[3],  data[4],
                    data[5],  data[6],  data[7]
                    		);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void fillRPanelCommodity(WebDriver driver,
            String Bank_Name, 
            String Bank_Contract,
            String Convert_Value,
            String Convert_Value_Type,
            String Extra_Charges,
            String Extra_Value_Type,
            String Sequence_Number,
            String Active) {
    	try {
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//span[contains(text(),'Masters')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'Rpanel Bank Name')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),' Add')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("bcontract_symbol")).sendKeys(Bank_Name);
            
            
            WebElement Contract = driver.findElement(By.id("bcontract_rate"));
            Select Select_Contract = new Select(Contract);
            Select_Contract.selectByVisibleText(Bank_Contract);
            
            Thread.sleep(2000);
            driver.findElement(By.id("bconvert_value")).sendKeys(Convert_Value);
            
            Thread.sleep(2000);
            WebElement Convert_type = driver.findElement(By.id("bconvert_value_type"));
            Select Select_Convert_type = new Select(Convert_type);
            Select_Convert_type.selectByVisibleText(Convert_Value_Type);
    		
            driver.findElement(By.id("bextra_charges")).clear();
            driver.findElement(By.id("bextra_charges")).sendKeys(Extra_Charges);
           
            Thread.sleep(2000);
            WebElement Extra_type = driver.findElement(By.id("bextra_type"));
            Select Select_Extra_type = new Select(Extra_type);
            Select_Extra_type.selectByVisibleText(Extra_Value_Type);
            
            driver.findElement(By.id("b_orderno")).sendKeys(Sequence_Number);
            Thread.sleep(2000);
            if(Active.equalsIgnoreCase("Yes")) {
            	driver.findElement(By.xpath("//label[@for='com_active_yes']")).click();
            }else {
            	driver.findElement(By.xpath("//label[@for='com_active_noo']")).click();
            }
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            System.out.println("R panel Bank save successfully");
    	}
    		
            catch (Exception e) {
                e.printStackTrace();
            }
    }
    	
    }