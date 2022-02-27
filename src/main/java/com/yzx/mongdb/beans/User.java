package com.yzx.mongdb.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@ApiModel(value = "用户信息")
@Document(collection="user")
@Data
public class User {
    @ApiModelProperty(value = "名字")
    @Field("name")
    private String name;
    @ApiModelProperty(value = "电话")
    private String tel;
    @ApiModelProperty(value = "年龄")
    private Integer age;
}