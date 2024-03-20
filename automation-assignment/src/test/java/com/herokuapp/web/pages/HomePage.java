package com.herokuapp.web.pages;

import com.herokuapp.env.EnvConfig;
import com.herokuapp.web.basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {


    public HomePage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "signup")
    WebElement buttonSignup;

    public void clickOnSignup() {
        this.waitForClickable(this.buttonSignup);
        this.buttonSignup.click();
    }

    public void openUrl() {
        getDriver().get(EnvConfig.getWebEnvBaseUrl());
        getDriver().manage().window().maximize();
    }
}
