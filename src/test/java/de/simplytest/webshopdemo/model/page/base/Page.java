package de.simplytest.webshopdemo.model.page.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Page {
    public static final String baseUrl = "https://autoprojekt.simplytest.de/";
    public static final By bodyLocator = By.cssSelector("body");
    public WebDriver driver;
    public WebElement body;
    public FluentWait<WebDriver> wait;
    public static int breakpointWidth = 768;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .withMessage("ERROR! Failed to locate an element on page: " + driver.getCurrentUrl());
        this.body = this.wait.until(visibilityOf(this.driver.findElement(bodyLocator)));
    }

    public void get(String url) {
        driver.get(url);
    }
}
