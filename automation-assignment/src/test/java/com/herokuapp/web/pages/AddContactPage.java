package com.herokuapp.web.pages;

import com.herokuapp.web.basepage.BasePage;
import com.herokuapp.web.pojo.AddContractPojo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class AddContactPage extends BasePage {

    private final String ADD_CONTRACT_PAGE_HEADER = "Add Contact";

    public AddContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()=" + ADD_CONTRACT_PAGE_HEADER + "]")
    WebElement headerAddContractPage;
    @FindBy(id = "firstName")
    WebElement inputFirstName;

    @FindBy(id = "lastName")
    WebElement inputLastName;

    @FindBy(id = "birthdate")
    WebElement inputDOB;
    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "phone")
    WebElement inputPhone;

    @FindBy(id = "street1")
    WebElement inputStreet1;

    @FindBy(id = "street2")
    WebElement inputStreet2;

    @FindBy(id = "city")
    WebElement inputCity;

    @FindBy(id = "stateProvince")
    WebElement inputState;

    @FindBy(id = "postalCode")
    WebElement inputPostalCode;

    @FindBy(id = "country")
    WebElement inputCountry;

    @FindBy(id = "submit")
    WebElement buttonSubmit;

    public void addContract(AddContractPojo addContractData) {

        this.enterAddContractData(addContractData);
        this.clickOnSubmitButton();
        Reporter.log("Contract is added with data: "+addContractData);

    }

    public void enterAddContractData(AddContractPojo addContract) {
        this.waitForClickable(this.inputFirstName);
        this.inputFirstName.sendKeys(addContract.getFirstName());
        this.inputLastName.sendKeys(addContract.getLastName());
        this.inputDOB.sendKeys(addContract.getDob());
        this.inputEmail.sendKeys(addContract.getEmail());
        this.inputPhone.sendKeys(addContract.getPhone());
        this.inputStreet1.sendKeys(addContract.getStreetAdd1());
        this.inputStreet2.sendKeys(addContract.getStreetAdd2());
        this.inputCity.sendKeys(addContract.getCity());
        this.inputState.sendKeys(addContract.getState());
        this.inputPostalCode.sendKeys(addContract.getPostalCode());
        this.inputCountry.sendKeys(addContract.getCountry());
    }

    public void clickOnSubmitButton() {
        this.buttonSubmit.click();
    }
}
