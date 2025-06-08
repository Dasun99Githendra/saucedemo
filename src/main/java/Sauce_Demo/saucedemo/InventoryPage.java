package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

// Inventory page handles product listing, search, sort, and product count
public class InventoryPage extends BasePage {
	// Locators
    private By productTitles = By.className("inventory_item_name");
    private By sortDropdown = By.className("product_sort_container");
    private By itemPrices = By.className("inventory_item_price");
    private By addToCartButtonTemplate = By.xpath("//div[text()='%s']/ancestor::div[@class='inventory_item']//button");
    
    // Constructor initializes the driver instance
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Returns the list of all product titles displayed on the inventory page.
     * Useful for validating if specific products are visible.
     */
    public List<String> getAllProductTitles() {
        List<WebElement> titles = driver.findElements(productTitles);
        return titles.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    
    /**
     * Selects a sorting option from the dropdown.
     * Useful for validating sorting behavior for price or name.
     */
    public void sortBy(String visibleText) {
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(visibleText);
    }

    /**
     * Extracts and returns prices of the top N products.
     * Useful for sorting validation like descending price checks.
     */
    public List<Double> getTopProductPrices(int count) {
        return driver.findElements(itemPrices).stream()
            .limit(count)
            .map(e -> Double.parseDouble(e.getText().replace("$", ""))) // removes $ and parses to double
            .collect(Collectors.toList());
    }

    /**
     * Checks how many product titles include a specific keyword.
     * Useful for basic keyword-based search and filter validation.
     */
    public long countProductsWithKeyword(String keyword) {
        return getAllProductTitles().stream()
            .filter(title -> title.toLowerCase().contains(keyword.toLowerCase()))
            .count();
    }
    
    /**
     * Adds a specific product to the cart using dynamic XPath.
     * Useful when working with multiple items dynamically by name.
     */
    public void addToCart(String productName) {
        By addToCartButton = By.xpath(String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName));
        click(addToCartButton);
    }
    
    /**
     * Clicks on the first product from the inventory list.
     * Useful for verifying product detail page and navigation.
     */
    public void clickFirstProduct() {
        driver.findElement(By.className("inventory_item_name")).click();
    }
}

