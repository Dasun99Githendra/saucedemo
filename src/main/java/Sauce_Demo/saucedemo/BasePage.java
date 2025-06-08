package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// BasePage provides reusable methods and explicit waits
public class BasePage {
    protected WebDriver driver; // WebDriver instance shared across all child pages
    private WebDriverWait wait; // Explicit wait to use for element visibility and clickability.

    // Constructor initializes driver and sets default wait time (10 seconds)
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Waits until an element is visible on the page
     * Can use for stable interactions before reading or writing to a field
     */
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Waits until an element is clickable.
     * Ensures interaction won't fail due to element not being ready
     */
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Generic click method with wait included
     * Use this instead of direct driver.findElement().click() to avoid flaky tests
     */
    protected void click(By locator) {
        waitForClickability(locator).click();
    }
    
    /**
     * Types text into an input field after confirming it's visible
     * Ensures data entry doesn't break if the field is still loading
     */
    protected void type(By locator, String text) {
        waitForVisibility(locator).sendKeys(text);
    }
    
    /**
     * Retrieves visible text from any web element after confirming it's visible
     * Common for labels, messages, and dynamic UI values
     */
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }
}
