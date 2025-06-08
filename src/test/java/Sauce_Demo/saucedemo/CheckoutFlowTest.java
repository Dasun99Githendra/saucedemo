package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Sauce_Demo.saucedemo.*;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.time.Duration;

public class CheckoutFlowTest extends TestBase {

    @Test
    public void endToEndCheckoutValidation() throws IOException {
    	
    	//Login with valid credentials
    	LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        
        // Take screenshot after entering credentials
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/valid_credentials.png");
         
        loginPage.clickLogin();
        waitForPageToLoad(); // Ensure complete page load before interacting
        
        // Screenshot after successful login
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/successful_login.png");
        
        //Add two items to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");
        slowDown(1000); // slight delay to simulate real interaction
        inventoryPage.addToCart("Sauce Labs Bike Light");
        slowDown(1000); 
        // Screenshot after adding items to cart
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/add_to_cart.png");
        
        //Go to cart and validate items
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        slowDown(1000);
        // Screenshot after navigating to cart page
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/cart_page.png");
        
        // Assertions to confirm both expected items are in the cart
        Assert.assertTrue(cartPage.verifyItemInCart("Sauce Labs Backpack"));
        Assert.assertTrue(cartPage.verifyItemInCart("Sauce Labs Bike Light"));
        cartPage.clickCheckout();
        slowDown(1000);
        
        // Enter customer information
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName("Dasun");
        checkoutPage.enterLastName("Githendra");
        checkoutPage.enterPostalCode("10620");
        slowDown(1000);
        // Screenshot after customer information is entered
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/customer_information.png");
        checkoutPage.clickContinue();
        
        //Overview page validations
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        slowDown(1000);
        //Screenshot after navigating to overview page
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/checkout_overview.png");
        
        // Price verification logic — validates backend vs UI total (excluding tax)
        double expectedTotal = overviewPage.calculateExpectedTotal();
        double displayedTotal = overviewPage.getDisplayedTotalWithoutTax();
        Assert.assertEquals(displayedTotal, expectedTotal, "Total price mismatch");
        
        //Complete the order
        overviewPage.finishCheckout();
        slowDown(1000);
        
        //Confirmation message validate
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(completePage.confirmationDisplayed(), "Confirmation message not displayed");
        
        // Screenshot capturing order success
        ScreenshotUtil.takeScreenshot(driver,this.getClass().getSimpleName(),"screenshots/order_confirmation.png");
    }
}
