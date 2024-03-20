package com.herokuapp.web.pages;

import com.herokuapp.web.basepage.BasePage;
import com.herokuapp.web.pojo.AddContractPojo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactListPage extends BasePage {

    @FindBy(id = "add-contact")
    WebElement buttonAddContract;

    String getContactListTableEle(int index){
        return "//tr[@class='contactTableBodyRow']/td["+index+"]";
    }

    public ContactListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnAddContract() {
        this.waitForClickable(this.buttonAddContract);
        this.buttonAddContract.click();
    }

    public void verifyAddContract(AddContractPojo addContractData) {
        this.waitForClickable(this.buttonAddContract);
        this.waitAndVerifyElementText(getContactListTableEle(2), addContractData.getFirstName() + " " + addContractData.getLastName());
        this.verifyElementText(getContactListTableEle(3), addContractData.getDob());
        this.verifyElementText(getContactListTableEle(4), addContractData.getEmail());
        this.verifyElementText(getContactListTableEle(5), addContractData.getPhone());
        this.verifyElementText(getContactListTableEle(6), addContractData.getStreetAdd1() + " " + addContractData.getStreetAdd2());
        String cityStateZip = addContractData.getCity() + " " + addContractData.getState() + " " + addContractData.getPostalCode();
        this.verifyElementText(getContactListTableEle(7), cityStateZip);
        this.verifyElementText(getContactListTableEle(8), addContractData.getCountry());
    }
}
