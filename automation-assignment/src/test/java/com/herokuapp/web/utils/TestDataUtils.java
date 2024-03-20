package com.herokuapp.web.utils;

import com.github.javafaker.Faker;
import com.herokuapp.web.pojo.AddContractPojo;
import com.herokuapp.web.pojo.AddUserPojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataUtils {

    public  static AddUserPojo getAddUserData(){
        Faker faker = new Faker();
        String fName = faker.name().firstName();
        String lName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String pwd = faker.internet().password(8,10,true);
        return new AddUserPojo(fName,lName,email,pwd);
    }

    public static AddContractPojo getAddContractData(){
    AddContractPojo contractPojo=new AddContractPojo();
    return  contractPojo.getAddContractPojo();
    }

    public  static String getDataInFormat(String format, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
