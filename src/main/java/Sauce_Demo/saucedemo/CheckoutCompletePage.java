package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Handles post-checkout confirmation
public class CheckoutCompletePage extends BasePage {
	
	// Locator
    private By confirmationMessage = By.className("complete-header");
    
    // Constructor: Pass WebDriver to BasePage for shared utility methods
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Validates whether the confirmation message is displayed correctly.
     * Used to verify that the order flow completed successfully.
     */
    public boolean confirmationDisplayed() {
        return getText(confirmationMessage).equalsIgnoreCase("Thank you for your order!");
    }
}

