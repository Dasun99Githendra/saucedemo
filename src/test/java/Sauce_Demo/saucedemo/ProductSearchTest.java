package Sauce_Demo.saucedemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.util.List;

public class ProductSearchTest extends TestBase {

    @Test
    public void validateProductSearch() throws IOException {
    	
    	//Login with standard credentials
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        
        // Capture screenshot after entering credentials before clicking login
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "valid_credentials");
        
        // Submit login form and wait for navigation
        loginPage.clickLogin();
        slowDown(1000);
        
        // Load inventory page and validate product titles
        InventoryPage inventoryPage = new InventoryPage(driver);

        //Validate that "backpack" is in product titles
        List<String> titles = inventoryPage.getAllProductTitles(); // Get all product titles currently displayed
        
        // Validate if "backpack" is present in the displayed product list
        boolean found = titles.stream().anyMatch(title -> title.toLowerCase().contains("backpack")); 
        Assert.assertTrue(found, "Backpack not found in product titles"); 
        
        // Capture screenshot showing the search result state
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "search_result");

        //Click on first product
        inventoryPage.clickFirstProduct();
        slowDown(1000);
        
        // Capture screenshot of the product details page
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "product_details");

        //Navigate back to home page
        driver.navigate().back();
        slowDown(1000);
        
        // Capture screenshot after returning to home page
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "back_to_inventory");
    }
}

