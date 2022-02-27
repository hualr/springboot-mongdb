package com.yzx.mongdb.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "登陆日志")
@Data
public class LogBean {
    @ApiModelProperty(value = "时间")
    private Date date;
    @ApiModelProperty(value = "type")
    private String type;
    @ApiModelProperty(value = "提示")
    private String msg;
}
