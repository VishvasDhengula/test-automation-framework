package com.herokuapp.api.utils;

import com.herokuapp.env.EnvConfig;
import com.herokuapp.web.pojo.AddUserPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;

import static io.restassured.RestAssured.given;

/**
 * common methods for API stuffs
 */
public class APIUtiles {

    final static String BASE_URL = EnvConfig.getAPIEnvBaseUrl();
    final static String USERS_ENDPOINT = "/users";
    final static String USERS_ME_ENDPOINT = USERS_ENDPOINT+"/me";

    public static void setBasePath(){
        RestAssured.baseURI = BASE_URL;
        Reporter.log("Base URL is " + BASE_URL);
    }
    public static  Header getHeader(){
        return new Header("Authorization","Bearer {{token}}");
    }

    public static  Header getHeader(String token){
        return new Header("Authorization","Bearer "+token);
    }

    public static String registerUser(AddUserPojo addUserData){
        Reporter.log("Registering user with data " + addUserData);

        Response resUserReg = APIUtiles.post(addUserData, USERS_ENDPOINT);
        Assert.assertEquals(201, resUserReg.statusCode());

        String id = APIUtiles.getValueFromResponse(resUserReg, "user._id");
        Reporter.log("User registered successfully. Id is: " + id);

        String token = APIUtiles.getValueFromResponse(resUserReg, "token");
        Reporter.log("Fetched the token from request");
        return token;
    }

    public static void getRegisterUserAndVerify(AddUserPojo addUserData,String token){
        Response resUserGet = APIUtiles.get(USERS_ME_ENDPOINT, token);
        Assert.assertEquals(200, resUserGet.statusCode());
        APIUtiles.verifyRegisteredUserData(addUserData,resUserGet);
    }

    public static void upteRegisterUserAndVerify(AddUserPojo updateUserData,String token){
        Response res2 = APIUtiles.path(updateUserData, USERS_ME_ENDPOINT, token);
        Assert.assertEquals(200, res2.statusCode());
    }

    public static Response post(Object body,String endpoint){
        return given().contentType(ContentType.JSON)
                .body(body)
                .header(APIUtiles.getHeader())
                .post(endpoint)
                .then()
                .log().all()
                .extract().
                response();
    }

    public static Response path(Object body,String endpoint,String token){
        return given().contentType(ContentType.JSON)
                .body(body)
                .header(getHeader(token))
                .patch(endpoint)
                .then()
                .log().all()
                .extract().
                response();
    }

    public static Response get(String endpoint,String token){
        return given().contentType(ContentType.JSON)
                        .header(getHeader(token))
                        .get(endpoint)
                        .then()
                        .log().all()
                        .extract().
                        response();
    }

    public static String getValueFromResponse(Response response,String path){
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get(path);
    }

    public static void verifyRegisteredUserData(AddUserPojo addUserData,Response response){
        Assert.assertEquals(addUserData.getFirstName(), getValueFromResponse(response, "firstName"));
        Assert.assertEquals(addUserData.getLastName(), getValueFromResponse(response, "lastName"));
        Assert.assertEquals(addUserData.getEmail(), getValueFromResponse(response, "email"));

    }

    public static String loginUsingAPI(AddUserPojo addUserData) {
        Object obj="{\"email\": \""+addUserData.getEmail()+"\", \"password\": \""+addUserData.getPassword()+"\"}";
        Response response=APIUtiles.post(obj,"/users/login");
        String token = APIUtiles.getValueFromResponse(response, "token");
        Reporter.log("Fetched the token from request");
        return token;
    }
}
