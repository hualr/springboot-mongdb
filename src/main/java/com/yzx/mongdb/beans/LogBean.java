package com.yzx.mongdb.beans;

import javax.xml.crypto.Data;
import java.util.Date;

public class LogBean {

    private Date date;

    private String type;

    private String msg;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
