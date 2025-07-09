package Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class Bullion_Driver{
 
	public static WebDriver driver;
	
	 public static void main(String[] args) throws IOException {
		 
		 launchBrowser(); 
		  ExcelUtils();
		  //CloseBrowser(driver);
		  } 
//=====================================================================================================================================

	 //Launch Browser
	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium_driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		}
//=====================================================================================================================================
	public static Bullion_Driver ExcelUtils() throws IOException {
	    String filePath = Browser.Excel_Utils.FILE_PATH;
	    
         
	    List<String> sheetNames = Browser.Excel_Utils.getSheetNames(filePath);
	    List<String> functionsToExecute = Browser.Excel_Utils.getMasterSheetData(filePath);

	    System.out.println("Sheet Names: " + sheetNames);
	    System.out.println("Functions to Execute: " + functionsToExecute);

	    return new Bullion_Driver(functionsToExecute, sheetNames, filePath);
	}
//=====================================================================================================================================
	 // Execute Test Cases
		
		public Bullion_Driver(List<String> functionsToExecute, List<String> sheetNames,String filePath ){
	        LocalDateTime startTime=LocalDateTime.now();
	        System.out.println("Automation process started: " + startTime);
			
			for (String functionName : functionsToExecute) {
			    System.out.println("Running Function: " + functionName);

			    if (sheetNames.contains(functionName)) {
			        switch (functionName) {
			            case "Login":
			            	Browser.LoginAutomation.performLogin(driver);
			            	break; 
//			            case "commodity_master":
//			            	Browser.Commdity_master.commdity_add(driver);
//			             // Get and update status			                
//			                break;
//			            case "commodity_group":
//			            	Browser.Commdity_group.commdity_select(driver);
//			            	break;
			            	
//			            case "R Panel Commodity Type":
//			            	Browser.R_Panel_Commodity_Type.commdity_Rpanel(driver);
//			            	break; 	
//			            case "R Panel Bank Master":
//			            	Browser.RPanel_Bank_Name.commdity_RpanelBank(driver);
//			            	break;
//			            case "Contract Symbol":
//			            	Browser.Contract_Symbol.Add_Contract_Symbol(driver);
//			            	break;
			            	
//			            case "Premium group":
//			            	Browser.Premium_group.Select_Premimum(driver);
//			            	break;
			                
			            case "Customer":
			            	Browser.Customer_add.customer_profile(driver);
			            	break;
			            	
			        }
			    } else {
			        System.out.println("Invalid sheet name: " + functionName);
			    }
			}
	            LocalDateTime endTime = LocalDateTime.now();
	            System.out.println("Automation process completed: " + endTime);
	            RunningTime(startTime, endTime);
			}

//========================================================================================================================================
		//Running Time
		private void RunningTime(LocalDateTime startTime, LocalDateTime endTime) {
			// TODO Auto-generated method stub
       //   Temporal startTime = null;
		    Duration duration = Duration.between(startTime, endTime);
            System.out.println("Start Time = " + startTime);
            System.out.println("End Time   = " + endTime);
         // Extract hours, minutes, and seconds
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();

            // Display the result
            System.out.println("Time duration: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
		}
//==========================================================================================================================================
		//Close Browser

		public static void CloseBrowser(WebDriver driver) {
            if (driver != null) {
                driver.close();
                driver.quit();  
                System.out.println("CloseBrowser");
        }
     }
}
