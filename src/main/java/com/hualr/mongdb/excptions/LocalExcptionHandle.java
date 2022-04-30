package com.hualr.mongdb.excptions;

import com.hualr.mongdb.beans.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//@ControllerAdvice
public class LocalExcptionHandle {
    @Autowired
    private MongoTemplate mongoTemplate;

    @ExceptionHandler(NullPointerException.class)
    public void excptionA(NullPointerException ex) {
        LogBean logBean = new LogBean();
        logBean.setDate(new Date());
        logBean.setType(ex.getClass().toString());
        logBean.setMsg(ex.toString());
        //将异常记录在mongodb中
        mongoTemplate.save(logBean,"error_log");
    }

    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView excptionC(ArithmeticException ex) {
        LogBean logBean = new LogBean();
        logBean.setDate(new Date());
        logBean.setType(ex.getClass().toString());
        logBean.setMsg(ex.toString());
        mongoTemplate.save(logBean,"error_log");
        Map<String, String> map = new HashMap<>();
        map.put("code", "303");
        map.put("msg", "SSS");
        return new ModelAndView("error",map);
    }

    @ExceptionHandler(Exception.class)
    public void excptionB(Exception ex) {
        LogBean logBean = new LogBean();
        logBean.setDate(new Date());
        logBean.setType(ex.getClass().toString());
        logBean.setMsg(ex.getMessage());
        mongoTemplate.save(logBean,"error_log");
    }
}
