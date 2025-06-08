package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Handles the first step of the checkout form
public class CheckoutPage extends BasePage {
	
	private WebDriver driver;
	
	// Locators
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    
    // Constructor passes the driver to BasePage for common operations
    public CheckoutPage(WebDriver driver) {
        super(driver); // explicitly call parent constructor
        this.driver = driver;
    }
    
    /**
     * Enters the user's first name into the checkout form.
     * Used for personal detail validation during E2E checkout.
     */
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }
    
    /**
     * Enters the user's last name into the form.
     * Part of the customer identity fields required to continue checkout.
     */
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    
    /**
     * Fills in the postal code field.
     * Used for personal detail validation during E2E checkout.
     */
    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }
    
    /**
     * Clicks the 'Continue' button to proceed to the overview step.
     * Transitions the user to review their order before placing it.
     */
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    /**
     * Optional utility to enter all customer info and move forward.
     * Can reused in compact test flows.
     */
    public void enterCustomerInformation(String first, String last, String postal) {
        enterFirstName(first);
        enterLastName(last);
        enterPostalCode(postal);
        clickContinue();
    }
}

