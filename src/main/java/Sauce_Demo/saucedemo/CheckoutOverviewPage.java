package Sauce_Demo.saucedemo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

// Handles price validation and order summary before finishing the order
public class CheckoutOverviewPage extends BasePage {
    
	// Locators
    private By itemPrices = By.className("inventory_item_price");
    private By finishButton = By.id("finish");
    private By totalLabel = By.className("summary_subtotal_label");
    
    // Constructor - pass driver to the base for common actions
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Sums all item prices listed in the checkout summary.
     * Helps to manually calculate expected total before comparing with UI total.
     */
    public double calculateExpectedTotal() {
        List<String> prices = driver.findElements(itemPrices).stream()
            .map(e -> e.getText().replace("$", "")) // Clean $ from each price
            .toList(); // Collect as list of Strings
        return prices.stream()
        		.mapToDouble(Double::parseDouble) // Convert each price to double
        		.sum(); // Total expected value
    }
    
    /**
     * Gets the item total displayed by the UI (excluding tax).
     * This will be compared against our manually calculated total for validation.
     */
    public double getDisplayedTotalWithoutTax() {
        String totalText = getText(totalLabel).replace("Item total: $", ""); // Strip prefix
        return Double.parseDouble(totalText); // Convert to number
    }
    
    /**
     * Finalizes the order by clicking the 'Finish' button.
     * Used after price validation is done.
     */
    public void finishCheckout() {
        click(finishButton);
    }
}

