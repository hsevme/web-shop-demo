package de.simplytest.webshopdemo.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTests {
    public WebDriver driver;
    public Dimension desktop = new Dimension(1200, 800);
    public Dimension mobile = new Dimension(700, 800);

    @BeforeAll
    void setup() {
        driver = new ChromeDriver(getOptions());
    }

    private ChromeOptions getOptions() {
        return new ChromeOptions()
                /*.addArguments("--headless=new")*/;
    }

    @AfterAll
    void teardown() {
        driver.quit();
    }
}
