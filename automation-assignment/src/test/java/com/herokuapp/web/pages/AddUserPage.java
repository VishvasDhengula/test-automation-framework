package com.herokuapp.web.pages;

import com.herokuapp.web.basepage.BasePage;
import com.herokuapp.web.pojo.AddUserPojo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class AddUserPage extends BasePage {
    public AddUserPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement inputFirstName;

    @FindBy(id = "lastName")
    WebElement inputLastName;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(id = "submit")
    WebElement buttonSubmit;

    public void addUser(AddUserPojo addUserData) {
        this.enterAddUserData(addUserData);
        this.clickOnSubmitButton();
        Reporter.log("User is added with data: "+addUserData);

    }

    public void enterAddUserData(AddUserPojo addUser) {
        this.waitForClickable(this.inputFirstName);
        this.inputFirstName.sendKeys(addUser.getFirstName());
        this.inputLastName.sendKeys(addUser.getLastName());
        this.inputEmail.sendKeys(addUser.getEmail());
        this.inputPassword.sendKeys(addUser.getPassword());
    }

    public void clickOnSubmitButton() {
        this.buttonSubmit.click();

    }
}
