package com.yzx.mongdb.beans;

public class User {
    private String name;
    private String tel;
    private String age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
    public void setAge(String age) {
        this.age = age;
    }
}