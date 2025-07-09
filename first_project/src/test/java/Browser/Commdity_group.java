package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;


public class Commdity_group{
	
    public static void commdity_select(WebDriver driver) {
        try {
            String sheetName = "commodity_group";
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
                fillCommoditygroup(driver,
                    data[0],  data[1]
                    		);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void fillCommoditygroup(WebDriver driver,
            String Commodity_Group_Name,
            String Description) {
    	    Commdity_master commMas = new Commdity_master(); 
    	try {
             driver.findElement(By.xpath("//span[contains(text(),'Masters')]")).click();
             Thread.sleep(2000);
             driver.findElement(By.partialLinkText("Commodity Group")).click(); 
             Thread.sleep(2000);
             driver.findElement(By.xpath("//a[contains(text(),'Edit ')]")).click();
             Thread.sleep(7000);
             driver.findElement(By.id("com_group_name")).clear();
             driver.findElement(By.id("com_group_name")).sendKeys(Commodity_Group_Name);
             driver.findElement(By.id("com_group_desc")).clear();
             driver.findElement(By.id("com_group_desc")).sendKeys(Description); 
             List<String> commodityName=  commMas.commdity_add(driver);
             //String commodityName = "ShreeComm";
             System.out.println(commodityName);
             JavascriptExecutor js = (JavascriptExecutor) driver;
         	 js.executeScript("scroll(0, 500)");
         	 Thread.sleep(2000);
             //Browser.Commdity_master.commdity_add(driver);
             driver.findElement(By.xpath("//td[text()='" + commodityName + "']/following-sibling::td//input[@id='com_buy_active']")).click();
             driver.findElement(By.xpath("//td[text()='" + commodityName + "']/following-sibling::td//input[@id='com_sel_active']")).click();
             driver.findElement(By.xpath("//td[text()='" + commodityName + "']/following-sibling::td//input[@id='com_buy_trade']")).click();
             driver.findElement(By.xpath("//td[text()='" + commodityName + "']/following-sibling::td//input[@id='com_sel_trade']")).click();
             driver.findElement(By.xpath("//button[@type='submit']")).click();
    	 }
             
         catch (Exception e) {
              e.printStackTrace();
             }    
    	 }
    }
   
