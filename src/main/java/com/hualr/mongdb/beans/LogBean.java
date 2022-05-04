package com.hualr.mongdb.beans;

import java.util.Date;
import lombok.Data;

@Data
public class LogBean {
    private Date date;
    private String type;
    private String msg;
}
