package com.hualr.mongdb.beans;

import lombok.Data;

@Data
public class ResponesDto {

    private String msg;
    private Integer code;
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
