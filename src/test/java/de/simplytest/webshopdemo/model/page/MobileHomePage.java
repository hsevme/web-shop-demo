package de.simplytest.webshopdemo.model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileHomePage extends HomePage {
    public MobileHomePage(WebDriver driver) {
        super(driver);
        cartContentsLocator = By.className("footer-cart-contents");
        cartCountSuffix = "";
    }
}
