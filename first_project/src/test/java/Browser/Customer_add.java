package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;

public class Customer_add{

	public static void customer_profile (WebDriver driver){
        try {
            String sheetName = "Customer";
            String filePath = Excel_Utils.FILE_PATH;
            int totalRows = Excel_Utils.getValidRows(filePath, sheetName);

            for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
                List<String> rowData = new ArrayList<>();

                for (int col = 3; col <=26; col++) {
                    String cellValue = Excel_Utils.getCellData(filePath, sheetName, rowNum, col);
                    rowData.add(cellValue);
                }

                String[] data = rowData.toArray(new String[0]);
                

                // Call the form fill method
                fillCommodityForm(driver,
                    data[0],  data[1],  data[2],  data[3],  data[4],
                    data[5],  data[6],  data[7],  data[8],  data[9],
                    data[10], data[11], data[12], data[13], data[14],
                    data[15], data[16]
                );
                
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void fillCommodityForm(WebDriver driver,
    		String Trader_Name,
    		  String Alias_Name,
    		  String Company_Name,
    		  String Trader_type,
    		  String Mobile_No,
    		  String E_Mail_Id,
    		  String Whats_App_No,
    		  String Business_Type,
    		  String Address,
    		  String City,
    		  String GST_No,
    		  String Pan_No,
    		  String Remarks,
    		  String Reference,
    		  String Login_Name,
    		  String Password,
    		  String Retype_Password) {

        try {
        	driver.findElement(By.xpath("//span[contains(text(),'Masters')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'Trader (Customer & Supplier)')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();
            Thread.sleep(7000);

            driver.findElement(By.id("cus_name")).sendKeys(Trader_Name);
            driver.findElement(By.id("cus_alise_name")).sendKeys(Alias_Name);
            driver.findElement(By.id("cus_company_name")).sendKeys(Company_Name);

            WebElement Traders_type=driver.findElement(By.id("customer_type"));
            Select select1 = new Select(Traders_type);
            select1.selectByVisibleText(Trader_type);
            Thread.sleep(2000);
            driver.findElement(By.id("cus_mobile")).sendKeys(Mobile_No);

            driver.findElement(By.id("cus_email")).sendKeys(E_Mail_Id);
 
            driver.findElement(By.id("cus_whatsapp")).sendKeys(Whats_App_No);
            Thread.sleep(2000);
            
            if(Business_Type.equalsIgnoreCase("TCS")) {
            	driver.findElement(By.xpath("//label[@for='cus_tcs']")).click();
            }else if(Business_Type.equalsIgnoreCase("TDS")) {
            	driver.findElement(By.xpath("//label[@for='cus_tcstds']")).click();
            }
            
            driver.findElement(By.id("cus_address")).sendKeys(Address);
            Thread.sleep(2000);
            driver.findElement(By.id("cus_city")).sendKeys(City);
            
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollTo(0, 600);");

            driver.findElement(By.id("cus_gstno")).sendKeys(GST_No);
            Thread.sleep(2000);
            driver.findElement(By.id("cus_panno")).sendKeys(Pan_No);

            
            driver.findElement(By.id("cus_remarks")).sendKeys(Remarks);
            Thread.sleep(2000);
            if(Reference.equalsIgnoreCase("SMS")) {
            	driver.findElement(By.id("cus_sms_status")).click();
            }else if(Business_Type.equalsIgnoreCase("Email")) {
            	driver.findElement(By.id("cus_email_status")).click();
            }
           
            js1.executeScript("window.scrollTo(0, 500);");
            //driver.findElement(By.id("cus_login_password")).sendKeys(Login_Name);
            Thread.sleep(2000);
            driver.findElement(By.id("cus_login_password")).sendKeys(Password);
            
            driver.findElement(By.id("cus_login_con_password")).sendKeys(Retype_Password);
            Thread.sleep(4000);
            //driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
            Thread.sleep(4000);
            System.out.println("Save successfully");
            driver.findElement(By.xpath("//a[contains(text(),'Active')]")).click();
 
    }        
        
        catch (Exception e) {
        e.printStackTrace();
        }
        
    }
}
            


   

