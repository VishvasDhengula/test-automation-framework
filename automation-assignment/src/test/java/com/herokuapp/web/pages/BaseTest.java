package com.herokuapp.web.pages;

import com.herokuapp.web.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        this.driver = DriverFactory.getDriver();
    }


    @AfterMethod
    public void afterMethod() {
        try {
            driver.quit();
        } catch (Exception ignored) {

        }
    }
}
