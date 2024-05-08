package de.simplytest.webshopdemo.model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileHomePage extends HomePage {
    // hsev: home page is displayed as mobile, if narrower than 768px
    public MobileHomePage(WebDriver driver) {
        super(driver);
        cartContentsLocator = By.className("footer-cart-contents");
        cartCountSuffix = "";
    }
}
