package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import java.util.HashMap;
import java.util.Map;


public class TestBase {
    protected WebDriver driver;

    /**
     * Sets up Chrome browser before each test method.
      - Configures browser options
      - Disables Chrome notifications and password manager
      - Opens the SauceDemo login page
     */
    
    @BeforeMethod
    public void setUp() {
        // Automatically setup the ChromeDriver binary
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // start in incognito
        options.addArguments("--disable-notifications"); // Disable push notifications
        options.addArguments("--disable-infobars"); // Hide automation banner
        options.addArguments("--disable-extensions"); // Disable extensions
        options.addArguments("--start-maximized");  // Open maximized window

        // Disable browser-level password manager popup and notifications
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // Disable password prompt
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs); // Apply preferences

        // Launch Chrome browser with defined options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();  // Redundant safe-guard

        // Open the SauceDemo login page
        driver.get("https://www.saucedemo.com");
    }
    
    
    /**
     * Waits for the entire page to be fully loaded.
     * Uses JavaScriptExecutor to ensure document.readyState is 'complete'.
     */
    
    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> ((JavascriptExecutor) driver)
            .executeScript("return document.readyState").equals("complete"));
    }

    
    /**
     * Waits until a specified element becomes visible on the page.
     * This helps synchronize automation steps with real UI rendering.
     *
     * @param locator By locator of the element to wait for
     */
    public void waitForElementVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    
    /**
     * Utility method to slow down execution for debugging or visual tracking.
     * Useful when want to see intermediate steps or animations.
     *
     * @param millis Duration in milliseconds to pause the test execution
     */
    public void slowDown(int millis) {
        try {
            Thread.sleep(millis); // Wait for the specified milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Print stack trace if interrupted
        }
    }
    
    
    /**
     * Tears down the browser instance after each test method.
     * Ensures browser is closed and WebDriver is cleaned up.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser and end the session
        }
    } 
    
}


