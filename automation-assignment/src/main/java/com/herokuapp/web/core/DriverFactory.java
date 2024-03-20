package com.herokuapp.web.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import java.net.URL;

public class DriverFactory {

    public enum BrowserType {
        CHROME, FIREFOX, IE
    }

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser");
        Reporter.log("Test will run on " + browser + " browser ");
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        WebDriver driver = null;
        switch (browserType) {
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                String ciJobId = System.getProperty("CI_JOB_ID");
                System.out.println("id "+ciJobId);
                if (ciJobId != null && !ciJobId.isEmpty()) {
                    System.out.println("in gitlab "+ciJobId);
                    options.addArguments("--headless=new");
                    try {
                        driver = new RemoteWebDriver(new URL("http://selenium:4444"), options);
                    } catch (Exception e) {
                        throw new Error("Browser not created in gitlab pipeline. " + e);
                    }
                } else {
                    driver = new ChromeDriver(options);
                }


            }
            case FIREFOX -> driver = new FirefoxDriver();
            case IE -> driver = new InternetExplorerDriver();
            default -> throw new Error("Browser Type should be valid");
        }
        Reporter.log(browser + " driver instance created successfully");
        return driver;

    }
}
