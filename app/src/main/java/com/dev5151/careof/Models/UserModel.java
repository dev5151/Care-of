package com.dev5151.careof.Models;

public class UserModel {
    private String name;
    private String uid;
    private String age;
    private String gender;

    public UserModel(String name, String uid, String age, String gender) {
        this.name = name;
        this.uid = uid;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return uid;
    }

    public void setEmail(String uid) {
        this.uid = uid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
