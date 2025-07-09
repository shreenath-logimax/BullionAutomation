package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;



public class LoginAutomation {


	public static void performLogin(WebDriver driver) {
        try {
            String sheetName = "Login";
            String filePath = Excel_Utils.FILE_PATH;
            int totalRows = Excel_Utils.getValidRows(filePath, sheetName);

            for (int rowNum = 1; rowNum <= totalRows; rowNum++) {

            	String expectedStatus = Excel_Utils.getCellData(filePath, sheetName, rowNum, 3);
                String url = Excel_Utils.getCellData(filePath, sheetName, rowNum, 4);
                String username = Excel_Utils.getCellData(filePath, sheetName, rowNum, 5);
                String password = Excel_Utils.getCellData(filePath, sheetName, rowNum, 6);

                driver.get(url);

                WebElement usernameField = driver.findElement(By.name("user_name"));
                usernameField.sendKeys(username);

                WebElement passwordField = driver.findElement(By.name("user_password"));
                passwordField.clear();
                passwordField.sendKeys(password);

                Thread.sleep(2000);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

                WebElement loginButton = driver.findElement(By.id("login"));
                loginButton.click();

                Thread.sleep(5000);

                String actualStatus;
                
                boolean isDashboardPresent;
                try {
                    isDashboardPresent = driver.findElement(By.xpath("//h4[contains(text(), 'Dashboard')]")).isDisplayed();
                } catch (Exception e) {
                    isDashboardPresent = false;
                }

                if ("valid Credentials".equalsIgnoreCase(expectedStatus)) {
                    if (isDashboardPresent) {
                        actualStatus = "Login successful";
                        Excel_Utils.setCellData(filePath, sheetName, rowNum, 1, "Pass");
                    } else {
                        actualStatus = "Login unsuccessful";
                        Excel_Utils.setCellData(filePath, sheetName, rowNum, 1, "Fail");
                    }
                } else if ("Invalid Credentials".equalsIgnoreCase(expectedStatus)) {
                    if (isDashboardPresent) {
                        actualStatus = "Login successful";
                        Excel_Utils.setCellData(filePath, sheetName, rowNum, 1, "Fail");
                    } else {
                        actualStatus = "Login Unsuccessful";
                        Excel_Utils.setCellData(filePath, sheetName, rowNum, 1, "Pass");
                    }
                } else {
                    actualStatus = "Invalid option";
                    Excel_Utils.setCellData(filePath, sheetName, rowNum, 1, "Skip");
                }

                Excel_Utils.setCellData(filePath, sheetName, rowNum, 2, actualStatus);
                String status = Browser.Excel_Utils.getStatus(filePath, sheetName);
                Browser.Excel_Utils.updateMasterStatus(filePath, status, sheetName);
            }

        } catch (Exception e) {
            System.out.println("Error in LoginAutomation.performLogin: " + e.getMessage());
            e.printStackTrace();
        }
    }
}