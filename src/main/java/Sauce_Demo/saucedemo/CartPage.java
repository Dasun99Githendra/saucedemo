package Sauce_Demo.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Handles cart page functionalities
public class CartPage extends BasePage {
	
	// Locators
    private By cartButton = By.className("shopping_cart_link");
    private By checkoutButton = By.id("checkout");

    // Constructor: initialize driver and pass to base class
    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the cart icon to open the cart page.
     * Used right after adding items to validate contents.
     */
    public void openCart() {
        click(cartButton);
    }

    /**
     * Validates whether the given item is present in the cart.
     * Used to confirm correct product before checkout.
     *
     * @param itemName - Name of the product to check
     * @return true if item is in the cart, else false
     */
    public boolean verifyItemInCart(String itemName) {
        By itemLocator = By.xpath("//div[text()='" + itemName + "']");
        return driver.findElements(itemLocator).size() > 0;
    }

    /**
     * Proceeds from cart to checkout step.
     * This is triggered after confirming cart contents.
     */
    public void clickCheckout() {
        click(checkoutButton);
    }
}

