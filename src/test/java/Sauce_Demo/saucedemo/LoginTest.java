package Sauce_Demo.saucedemo;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import Sauce_Demo.saucedemo.LoginPage;
import utils.ScreenshotUtil;
import Sauce_Demo.saucedemo.InventoryPage;

public class LoginTest extends TestBase {

    @Test
    public void validateLoginFunctionality() throws IOException {
    	
    	//Login with valid credentials
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        
        // Capture screenshot before click on login
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/valid_credentials.png");
        
        // Click the login button
        loginPage.clickLogin();
        slowDown(1000); // small pause to allow page transition
        
        // Assert that redirected to the inventory page
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Redirection to inventory failed");
        
        // Validate that inventory is not empty after login
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.getAllProductTitles().size() > 0, "Inventory is empty");
        
        // Capture screenshot after successful login and inventory load
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/successful_login.png");
        
    }
}

