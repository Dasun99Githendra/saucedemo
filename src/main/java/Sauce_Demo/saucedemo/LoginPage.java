package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Handles login functionality using POM structure
public class LoginPage extends BasePage {
	
	// Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    /**
     * Constructor to initialize the WebDriver instance through the BasePage.
     *
     * @param driver - active WebDriver instance passed from the test class
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Full login method – used when both username and password are provided together.
     * Useful for quick one-step login flows.
     */
    public void login(String username, String password) {
        type(usernameField, username); // Enter username
        type(passwordField, password); // Enter password
        click(loginButton); // Click login to proceed
    }
    
    /**
     * Enters the username in the login input field.
     * Can use when need to control login steps separately for screenshots or validations.
     */
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    //Enters the password in the password input field.
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    
    // Often used after entering credentials in separate steps.
    public void clickLogin() {
        click(loginButton);
    }
}

