package de.simplytest.webshopdemo.model.page;

import de.simplytest.webshopdemo.model.page.base.Page;
import de.simplytest.webshopdemo.model.page.components.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static de.simplytest.webshopdemo.actions.Scrolling.scrollToElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HomePage extends Page {
    public static final String url = "/";
    Product product;
    By headlineLocator = By.className("page-title");
    By cartContentsLocator = By.className("cart-contents");
    By cartButtonLocator = By.className("added_to_cart");
    By cartCountLocator = By.className("count");
    String cartCountSuffix = " items";

    public HomePage(WebDriver driver) {
        super(driver);
        product = new Product(driver.findElement(By.cssSelector("ul.products")));
    }

    public HomePage get() {
        get(baseUrl + url);
        return getHomePageAccordingToWidth();
    }

    public String headline() {
        return wait.until(visibilityOfElementLocated(headlineLocator)).getText();
    }

    public HomePage addProductToCart(String productName) {
        WebElement addToCartButton = product.addToCart(productName);
        scrollToElement(driver, addToCartButton);
        addToCartButton.click();
        return getHomePageAccordingToWidth();
    }

    public boolean cartIsEmpty() {
        WebElement cart = driver.findElement(cartContentsLocator);
        WebElement cartCount = cart.findElement(cartCountLocator);
        scrollToElement(driver, cartCount);
        return cartCount.getText().equals(String.format("0%s", cartCountSuffix));
    }

    public CartPage viewCart() {
        WebElement viewCartButton = wait.until(elementToBeClickable(cartButtonLocator));
        scrollToElement(driver, viewCartButton);
        viewCartButton.click();
        return getCartPageAccordingToWidth();
    }

    public CartPage getCartPageAccordingToWidth() {
        return driver.manage().window().getSize().width < Page.breakpointWidth ? new MobileCartPage(driver) : new CartPage(driver);
    }

    public HomePage getHomePageAccordingToWidth() {
        return driver.manage().window().getSize().width < Page.breakpointWidth ? new MobileHomePage(driver) : this;
    }
}
