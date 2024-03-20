package com.herokuapp.web.pojo;

import com.github.javafaker.Faker;
import com.herokuapp.web.utils.TestDataUtils;

public class AddContractPojo {

    private final String firstName;
    private final String lastName;
    private final String dob;
    private final String email;
    private final String phone;
    private final String streetAdd1;
    private final String streetAdd2;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    public AddContractPojo() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.dob = TestDataUtils.getDataInFormat("yyyy-MM-dd",faker.date().birthday());
        this.email = faker.internet().emailAddress();
        this.phone = faker.number().digits(10);
        this.streetAdd1 = faker.address().streetName();
        this.streetAdd2 = faker.address().streetAddressNumber();
        this.city = faker.address().city();
        this.state = faker.address().state();
        this.postalCode = faker.address().zipCode();
        this.country = faker.address().country();

    }

    public AddContractPojo getAddContractPojo() {
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreetAdd1() {
        return streetAdd1;
    }

    public String getStreetAdd2() {
        return streetAdd2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Add Contract Data{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", streetAdd1='" + streetAdd1 + '\'' +
                ", streetAdd2='" + streetAdd2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
