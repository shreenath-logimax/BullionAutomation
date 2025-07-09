package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;

public class Premium_group {

	public static void Select_Premimum(WebDriver driver) {
		try {
            String sheetName = "Premium group";
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
                    data[0],  data[1],data[2],data[3],data[4]
                    		);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	

	public static void fillRPanelCommodity(WebDriver driver,
	    String Group_Name, 
	    String Description,
	    String Active,
	    String Buy_Discount,
	    String Sell_Discount) { 
		Commdity_master commMas = new Commdity_master();
	 try {
			 driver.findElement(By.xpath("//span[contains(text(),'Masters')]")).click();
	     Thread.sleep(2000);
	     JavascriptExecutor js = (JavascriptExecutor) driver;
     	 js.executeScript("scroll(0, 500)");
	     driver.findElement(By.xpath("//span[contains(text(),'Premium Group')]")).click();
	     Thread.sleep(2000);
	     driver.findElement(By.xpath("//a[contains(text(),' Add')]")).click();
	     Thread.sleep(2000);
	     driver.findElement(By.id("prem_group_name")).sendKeys(Group_Name);
	     
	     Thread.sleep(2000);
	     driver.findElement(By.id("prem_group_desc")).sendKeys(Description);
	     
	     if(Active.equalsIgnoreCase("Yes")) {
	     	driver.findElement(By.xpath("//label[@class='btn btn-primary active']")).click();
	     }else {
	     	driver.findElement(By.xpath("//label[@class='btn btn-primary']")).click();
	     }
	     js.executeScript("scroll(0, 700)");
	     Thread.sleep(2000);
	     //List<String> commodityName=  commMas.commdity_add(driver);
	     List<String> commodityName = new ArrayList<>();
	     commodityName.add("ShreeComm");
	     System.out.println(commodityName.getFirst());
	     driver.findElement(By.xpath("//td[text()='" + commodityName.get(0) + "']/following-sibling::td//input[@id='prem_combuy_active']")).click();
         driver.findElement(By.xpath("//td[text()='" + commodityName.get(0) + "']/following-sibling::td//input[@id='prem_comsell_active']")).click();
         Thread.sleep(2000);
         driver.findElement(By.xpath("//td[text()='" + commodityName.get(0) + "']/following-sibling::td//input[@class='form-control'])[1]")).click();
         driver.findElement(By.xpath("//td[text()='" + commodityName.get(0) + "']/following-sibling::td//input[@class='form-control'])[2]")).click();
       
         
	     driver.findElement(By.xpath("//button[@type='submit']")).click();
	     System.out.println("Premium group  successfully");
	 }
	  catch (Exception e) {
	         e.printStackTrace();
	     }
	}
	 
	}