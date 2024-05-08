package de.simplytest.webshopdemo.model.page;

import de.simplytest.webshopdemo.model.page.base.Page;
import de.simplytest.webshopdemo.model.page.components.Cart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static de.simplytest.webshopdemo.actions.Scrolling.scrollToElement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartPage extends Page {
    Cart cart;
    String url = "/cart";
    By headlineLocator = By.className("entry-title");

    public CartPage(WebDriver driver) {
        super(driver);
        if (!headline().equals("Cart")) {
            throw new IllegalStateException("This is not the Cart Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        cart = new Cart(this.driver.findElement(By.className("woocommerce-cart")));
    }

    public CartPage get() {
        get(baseUrl + url);
        return getCartPageAccordingToWidth();
    }

    public String headline() {
        return wait.until(visibilityOfElementLocated(headlineLocator)).getText();
    }

    public String getTotalPrice() {
        WebElement orderTotal = wait.until(visibilityOfElementLocated(cart.orderTotal));
        WebElement price = wait.until(visibilityOf(orderTotal.findElement(cart.amount)));
        return price.getText();
    }

    public CartPage setQuantityOfProduct(String productName, int quantity) {
        WebElement cartItem = cart.getItem(productName);
        WebElement quantityField = cartItem.findElement(cart.quantityField);
        quantityField.clear();
        quantityField.sendKeys(String.format("%d", quantity));
        WebElement updateCartButton = driver.findElement(cart.updateButton);
        scrollToElement(driver, updateCartButton);
        updateCartButton.click();
        WebElement message = wait.until(visibilityOfElementLocated(cart.message));
        assertThat(message.getText()).isEqualTo("Cart updated.");
        return getCartPageAccordingToWidth();
    }

    public CartPage getCartPageAccordingToWidth() {
        return driver.manage().window().getSize().width < Page.breakpointWidth ? new MobileCartPage(driver) : new CartPage(driver);
    }
}
