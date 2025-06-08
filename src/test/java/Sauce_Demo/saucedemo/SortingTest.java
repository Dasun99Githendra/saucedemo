package Sauce_Demo.saucedemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import Sauce_Demo.saucedemo.InventoryPage;
import Sauce_Demo.saucedemo.LoginPage;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.util.List;

public class SortingTest extends TestBase {

    @Test
    public void validateSortingAndPriceExtraction() throws IOException {
    	
    	//Login with valid credentials
    	LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        
        // Capture screenshot after entering credentials before clicking login
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/valid_credentials.png");
        
        // Submit login form and wait for navigation
        loginPage.clickLogin();
        slowDown(1000); // small delay to allow navigation and page rendering
        
        // Capture screenshot after successful login
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/successful_login.png");
        
        // Perform sorting action on inventory page
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortBy("Price (high to low)"); // dropdown selection for sorting
        
        // Capture screenshot after click on dropdown
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/dropdown_selection.png");
        
        // Extract top 3 prices to validate sorting logic
        List<Double> prices = inventoryPage.getTopProductPrices(3); 
        
        // Assertion to ensure prices are actually sorted in descending order
        Assert.assertTrue(prices.get(0) >= prices.get(1) && prices.get(1) >= prices.get(2), "Prices are not sorted in descending order");

        // Count how many product titles contain the keyword 'sauce'
        long sauceCount = inventoryPage.countProductsWithKeyword("sauce");
        
        // No pass/fail here, but asserting non-negative count as a sanity check.
        Assert.assertTrue(sauceCount >= 0, "Product title keyword count failed");
        
        // Screenshot showing sorted product state
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/Sort_H-L.png");
    }
}
