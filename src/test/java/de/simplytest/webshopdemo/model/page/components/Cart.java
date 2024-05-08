package de.simplytest.webshopdemo.model.page.components;

import de.simplytest.webshopdemo.model.page.components.base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Cart extends BaseComponent {
    public By orderTotal = By.className("order-total");
    public By amount = By.className("amount");
    public By message = By.className("woocommerce-message");
    public String itemTemplate = "//td[@class='product-name']/a[text()='%s']/../..";
    public By updateButton = By.cssSelector("button[name='update_cart']");
    public By quantityField = By.cssSelector("input[title='Qty']");

    public Cart(WebElement root) {
        super(root);
    }

    public WebElement getItem(String productName) {
        return root.findElement(By.xpath(String.format(itemTemplate, productName)));
    }

}
