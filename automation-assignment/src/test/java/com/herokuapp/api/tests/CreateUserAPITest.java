package com.herokuapp.api.tests;

import com.herokuapp.api.utils.APIUtiles;
import com.herokuapp.web.pojo.AddUserPojo;
import com.herokuapp.web.utils.TestDataUtils;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateUserAPITest {

    /**
     * This API test will register a user, then get the registered user, then update the same user
     *
     * Test Data is generated using Faker Java API and
     */
    @Test
    public void createUser() {

        APIUtiles.setBasePath();
        AddUserPojo addUserData = TestDataUtils.getAddUserData();

        String token = APIUtiles.registerUser(addUserData);
        APIUtiles.getRegisterUserAndVerify(addUserData, token);

        AddUserPojo updateUserData = TestDataUtils.getAddUserData();
        APIUtiles.upteRegisterUserAndVerify(updateUserData, token);

        Reporter.log("API test finished.....");
    }
}
