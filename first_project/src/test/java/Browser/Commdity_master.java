package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;

public class Commdity_master {

    public static List<String> commdity_add(WebDriver driver) {
    	List<String> commodityNames = new ArrayList<>();
        try {
            String sheetName = "commodity_master";
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
                fillCommodityForm(driver,
                    data[0],  data[1],  data[2],  data[3],  data[4],
                    data[5],  data[6],  data[7],  data[8],  data[9],
                    data[10], data[11], data[12], data[13], data[14],
                    data[15], data[16], data[17], data[18], data[19],
                    data[20], data[21]
                );
                commodityNames.add(data[0]);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return commodityNames;
    }
    
    public static void fillCommodityForm(WebDriver driver,
        String Commodity_Name, 
        String R_Panel_Commodity_Type, 
        String Weight_In_Grm,
        String Other_charges, 
        String Is_Madurai_Rate, 
        String Is_Bar_Selection,
        String Bar_Quantity, 
        String Quantity_type,
        String No_of_bars, 
        String Decimals_for_rate,
        String Decimals_for_qty, 
        String Margin_Type,
        String Margin_Value, 
        String Active, 
        String Purity_Settings,
        String Purity_Conversation, 
        String Display_Purity, 
        String Round_off_factor,
        String Sequence_Number_Update, 
        String Trading_Status, 
        String Sell_status,
        String Buy_status) {

        try {
        	driver.findElement(By.xpath("//span[contains(text(),'Masters')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'Commodity Master')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();
            Thread.sleep(7000);

            driver.findElement(By.id("com_name")).sendKeys(Commodity_Name);
            driver.findElement(By.id("com_type")).sendKeys(R_Panel_Commodity_Type);
            driver.findElement(By.id("com_weight")).sendKeys(Weight_In_Grm);

            if (!Other_charges.isBlank()) {
                WebElement otherCharge = driver.findElement(By.id("com_other_charges"));
                otherCharge.clear();
                otherCharge.sendKeys(Other_charges);
                System.out.println("Run");
            }
            if(Purity_Settings.equalsIgnoreCase("on")) {
        		driver.findElement(By.xpath("//label[@for='com_isregion_yes']")).click();
        		WebElement Purity_dropwon = driver.findElement(By.id("com_calpurity"));
        		Select Select_Purity = new Select(Purity_dropwon);
        		Select_Purity.selectByVisibleText(Purity_Conversation);

            if(Purity_Settings.equalsIgnoreCase("0ff")) {
            	driver.findElement(By.xpath("//label[@for='com_isregion_no']")).click();
            	driver.findElement(By.id("com_display_purity")).sendKeys(Display_Purity);
            }
            WebElement Purity_dropdown=driver.findElement(By.id("com_correction_type"));
            Select select1 = new Select(Purity_dropdown);
            select1.selectByVisibleText(Round_off_factor);
            driver.findElement(By.id("com_order_number")).sendKeys(Sequence_Number_Update);

//            if (Is_Madurai_Rate.equalsIgnoreCase("Yes"))
//            	Thread.sleep(7000);{
//                driver.findElement(By.xpath("(//label[@class='btn btn-primary business active'])")).click();
//            }
            if (Is_Bar_Selection.equalsIgnoreCase("yes")) {	
            	Thread.sleep(7000);
            	JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				Thread.sleep(5000);
                WebElement bar_selection=driver.findElement(By.xpath("//label[@for='bar_selection_yes']"));
                bar_selection.click();
                driver.findElement(By.id("com_bar_quantity")).sendKeys(Bar_Quantity);
                System.out.println(Bar_Quantity);
                WebElement dropdown=driver.findElement(By.id("com_bar_type"));
                Select select2 = new Select(dropdown);
                
                if (Quantity_type=="Grams") {
                    select2.selectByValue("0");  // Value should be passed as String
                }
                select1.selectByValue("1");
                driver.findElement(By.id("com_bar_no")).sendKeys(No_of_bars);
                System.out.println("No_of_bars:" +  No_of_bars);     
            }
            else if (Decimals_for_rate != null && !Decimals_for_rate.isBlank()) {
            	driver.findElement(By.id("com_roundoff")).clear();
            	driver.findElement(By.id("com_roundoff")).sendKeys(Decimals_for_rate);
            	driver.findElement(By.id("allowed_decimals")).clear();
            	driver.findElement(By.id("allowed_decimals")).sendKeys(Decimals_for_qty);
                System.out.println("a has a value: " + Decimals_for_rate);
            }  
            WebElement dropdown=driver.findElement(By.id("com_margin_type"));
            Select select2 = new Select(dropdown);
            if (Margin_Type.equalsIgnoreCase("Percentage")) {
                select2.selectByValue("0");  // Value should be passed as String
            }
            select2.selectByValue("0");
            System.out.println("a has a value: ");
        	Thread.sleep(10000);
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        	driver.findElement(By.id("com_margin_value")).sendKeys(Margin_Value);
        	if (Active.equalsIgnoreCase("yes")) {	
        	   WebElement Active1 = driver.findElement(By.xpath("//label[@for='com_active_yes']"));
        			      Active1.click(); 
        	}
            if (Active.equalsIgnoreCase("No")) {
        	   WebElement Active2 = driver.findElement(By.xpath("//label[@for='com_active_no']"));
        			   Active2.click();		   
        	}
        	 Thread.sleep(7000);
        	if (Active.equalsIgnoreCase("No")) {
                 WebElement Active2 = driver.findElement(By.xpath("//label[@for='add_status_no']"));
              			   Active2.click();	
            }
        	if (Active.equalsIgnoreCase("yes")) {
        		System.out.println(Active);
         	    WebElement Active1 = driver.findElement(By.xpath("//label[@for='add_status_yes']"));
         			       Active1.click(); 		   
	            Thread.sleep(7000);		   
	            driver.findElement(By.xpath("//label[@for='sell_status_on']")).click();	 
	            driver.findElement(By.xpath("//label[@for='buy_status_on']")).click();
	            Thread.sleep(7000);
	            driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
	            System.out.println("Save successfully");
	            JavascriptExecutor js1 = (JavascriptExecutor) driver;
	            js1.executeScript("window.scrollTo(0, 0);");
        	}
        } 
     }      
            catch (Exception e) {
            e.printStackTrace();
        }
    }        
}


