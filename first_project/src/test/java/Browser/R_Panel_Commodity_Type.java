package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;


public class R_Panel_Commodity_Type{
	
    public static void commdity_Rpanel(WebDriver driver) {
        try {
            String sheetName = "R Panel Commodity Type";
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
                    data[5],  data[6],  data[7],  data[8],  data[9]
                    		);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void fillRPanelCommodity(WebDriver driver,
            String Display_Name, 
            String MCX_Symbol,
            String Commodity_Type,
            String Bank_Symbol,
            String Sell_Tax,
            String Sell_TCS,
            String Buy_Tax,
            String Buy_TCS,
            String Sequence_Number,
            String Active) {
    	try {
    		driver.findElement(By.xpath("//span[contains(text(),'Masters')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'R-Panel Commodity Type')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),' Add')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("rcom_disname")).sendKeys(Display_Name);
            
            if(Commodity_Type.equalsIgnoreCase("GOld")) {
            	driver.findElement(By.xpath("//label[@for='com_type_gold']")).click();
            }else {
            	driver.findElement(By.xpath("//label[@for='com_active_no']")).click();
            }
            WebElement Symbol = driver.findElement(By.id("rcom_mcxsymbol"));
            Select Select_symbol = new Select(Symbol);
            Select_symbol.selectByVisibleText(MCX_Symbol);
            
            WebElement BankSymbol = driver.findElement(By.id("rcom_banksymbol"));
            Select Select_bank_symbol = new Select(BankSymbol);
            Select_bank_symbol.selectByVisibleText(Bank_Symbol);
    		
            driver.findElement(By.id("rcom_sell_tax")).sendKeys(Sell_Tax);
            driver.findElement(By.id("rcom_sell_tcs")).sendKeys(Sell_TCS);
            
            driver.findElement(By.id("rcom_buy_tax")).sendKeys(Buy_Tax);
            driver.findElement(By.id("rcom_buy_tcs")).sendKeys(Buy_TCS);
            
            driver.findElement(By.id("rcom_orderno")).sendKeys(Sequence_Number);
            
            if(Active.equalsIgnoreCase("Yes")) {
            	driver.findElement(By.xpath("//label[@for='com_active_yes']")).click();
            }else {
            	driver.findElement(By.xpath("//label[@for='com_active_noo']")).click();
            }
            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
            System.out.println("R Panel Commodity Type save successfully");
    	}
    		
            catch (Exception e) {
                e.printStackTrace();
            }
    }
    	
    }