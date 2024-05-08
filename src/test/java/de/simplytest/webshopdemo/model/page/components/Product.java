package de.simplytest.webshopdemo.model.page.components;

import de.simplytest.webshopdemo.model.page.components.base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product extends BaseComponent {

    public Product(WebElement root) {
        super(root);
    }

    public WebElement addToCart(String productSku) {
        return root.findElement(By.cssSelector(String.format("a[data-product_sku='%s']", productSku)));
    }
}
