package de.simplytest.webshopdemo.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTests {
    public WebDriver driver;
    public Dimension desktop = new Dimension(1200, 800);
    public Dimension mobile = new Dimension(700, 800);

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(getOptions());
    }

    private ChromeOptions getOptions() {
        return new ChromeOptions();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
