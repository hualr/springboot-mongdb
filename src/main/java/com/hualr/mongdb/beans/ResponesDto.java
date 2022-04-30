package com.hualr.mongdb.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "响应实体类")
@Data
public class ResponesDto {

    @ApiModelProperty(value = "提示")
    private String msg;
    @ApiModelProperty(value = "响应码")
    private Integer code;
    @ApiModelProperty(value = "响应数据")
    private Object data;

    public ResponesDto(String msg, Integer code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static ResponesDto ok() {
        return new ResponesDto("操作成功", 200, true);
    }

    public static ResponesDto ok(String msg, Integer code, Object data) {
        return new ResponesDto(msg, 200, data);
    }

    public static ResponesDto error() {
        return new ResponesDto("操作失败", 500, false);
    }

    public static ResponesDto error(String msg, Integer code, Object data) {
        return new ResponesDto(msg, 500, data);
    }
}
