package com.hualr.mongdb.beans;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class User {
    @Field("name")
    private String name;
    private String tel;
    private Integer age;

    public PersonFeature personFeature;
    @Data
    public static  class PersonFeature {
        private String school;
        private String country;
    }
}