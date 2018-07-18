package com.referazi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;

    private String name;

    private String email;

    private String gender;

    private String phone;

    private String password;

    private String isSeeker = "false";

    private String isProvider = "false";

    private String isBlogger = "false";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getIsSeeker() {
        return isSeeker;
    }

    public void setIsSeeker(String isSeeker) {
        this.isSeeker = isSeeker;
    }

    public String getIsProvider() {
        return isProvider;
    }

    public void setIsProvider(String isProvider) {
        this.isProvider = isProvider;
    }

    public String getIsBlogger() {
        return isBlogger;
    }

    public void setIsBlogger(String isBlogger) {
        this.isBlogger = isBlogger;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", isSeeker='" + isSeeker + '\'' +
                ", isProvider='" + isProvider + '\'' +
                ", isBlogger='" + isBlogger + '\'' +
                '}';
    }
}