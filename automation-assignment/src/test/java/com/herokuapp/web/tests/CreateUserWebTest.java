package com.herokuapp.web.tests;

import com.herokuapp.api.utils.APIUtiles;
import com.herokuapp.web.pages.BaseTest;
import com.herokuapp.web.core.DriverFactory;
import com.herokuapp.web.pages.AddContactPage;
import com.herokuapp.web.pages.AddUserPage;
import com.herokuapp.web.pages.ContactListPage;
import com.herokuapp.web.pages.HomePage;
import com.herokuapp.web.pojo.AddContractPojo;
import com.herokuapp.web.pojo.AddUserPojo;
import com.herokuapp.web.utils.TestDataUtils;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateUserWebTest extends BaseTest {


    /**
     * This Web test will add a user,
     * then add a contract list
     * then verify added contract list
     * in last it fetch the added user using API and for that its need to token which using login API
     *
     * Test Data is generated using Faker Java API and
     */
    @Test
    public void createUserTest() {

        HomePage home = new HomePage(driver);
        home.openUrl();
        home.clickOnSignup();

        AddUserPojo addUserData = TestDataUtils.getAddUserData();
        AddUserPage user = new AddUserPage(driver);
        user.addUser(addUserData);

        ContactListPage contractList = new ContactListPage(driver);
        contractList.clickOnAddContract();

        AddContractPojo addContractData = TestDataUtils.getAddContractData();
        AddContactPage contract = new AddContactPage(driver);
        contract.addContract(addContractData);

        contractList.verifyAddContract(addContractData);

        APIUtiles.setBasePath();
        String token = APIUtiles.loginUsingAPI(addUserData);
        APIUtiles.getRegisterUserAndVerify(addUserData, token);
        Reporter.log("Web test finished.....");

    }

}
