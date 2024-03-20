package com.herokuapp.web.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

import java.time.Duration;

/**
 * Base class having the common methods and all the page classes needs to be extends this class.
 */
public abstract class BasePage {

    private final WebDriver driver;
    public static int WITH_TIMEOUT = 30;
    public static int POLLING_EVERY = 5;
    private Wait<WebDriver> wait;

    public BasePage(final WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    Wait<WebDriver> getFluentWaitObj() {
        if (wait != null) {
            return wait;
        }
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WITH_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING_EVERY))
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public void waitForClickable(WebElement ele) {
        Wait<WebDriver> wait = this.getFluentWaitObj();
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void verifyElementText(WebElement ele, String text) {
        Wait<WebDriver> wait = this.getFluentWaitObj();
        wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
        Reporter.log("Text '"+text+"' is present");
    }

    public void verifyElementText(String xpath, String text) {
        try {
            WebElement ele = driver.findElement(By.xpath(xpath));
            this.verifyElementText(ele, text);
        }catch (Exception e){
            throw new Error("Unable to verify text "+text);
        }
    }

    public void waitAndVerifyElementText(String xpath, String text){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            WebElement ele = driver.findElement(By.xpath(xpath));
            this.verifyElementText(ele, text);
        }catch (Exception e){
            throw new Error("Unable to verify text "+text);
        }
    }

}
