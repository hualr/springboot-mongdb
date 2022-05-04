package com.hualr.mongdb.controller;

import com.alibaba.fastjson.JSONObject;
import com.hualr.mongdb.beans.ResponesDto;
import com.hualr.mongdb.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo")
public class MongDBController {
    @Autowired
    private MongoTemplate mongoTemplate;
    int j = 2;



    @PostMapping("/save")
    public ResponesDto save() {
        for (int i = 0; i < 100L; i++) {
            User user = new User();
            user.setAge(15 + j + i);
            user.setName("张" + j + i);
            user.setTel("1782828282");
            final User.PersonFeature personFeature = new User.PersonFeature();
            personFeature.setCountry("chinese");
            personFeature.setSchool("柏树" + j + i);
            user.setPersonFeature(personFeature);
            User userTemp = mongoTemplate.save(user);
            System.out.println(userTemp);
        }

        j++;
        return ResponesDto.ok("操作成功", 200, null);
    }

    @DeleteMapping("/remove")
    public ResponesDto remove() {
        Query query = new Query(Criteria.where("name").is("张三"));
        mongoTemplate.remove(query, User.class);
        return ResponesDto.ok();
    }


    @GetMapping("/shell")
    public ResponesDto shell() {
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        // 设置可执行的存储过程脚本
        ExecutableMongoScript script = new ExecutableMongoScript("function() { return 3+3; }");
        // 注册存储过程，指定脚本名称test2
        scriptOps.register(new NamedMongoScript("add2", script));
        // 调用远程脚本，并设置参数
        Object call = scriptOps.call("add2");
        // 判断存储过程test是否存在
        boolean flag = scriptOps.exists("add2");
        System.out.println(flag);
        return ResponesDto.ok("操作成功", 200, String.valueOf(call));
    }

    @GetMapping("/shell2")
    public ResponesDto shell2() {
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        // 编写可执行的脚本
        ExecutableMongoScript script = new ExecutableMongoScript("function(x,y) { return x+y; }");
        // 注册存储过程，指定脚本名称test2
        scriptOps.register(new NamedMongoScript("test2", script));
        // 调用远程脚本，并设置参数
        Object call = scriptOps.call("test2", 1, 2);
        // 判断存储过程test是否存在
        boolean flag = scriptOps.exists("test");
        return ResponesDto.ok("操作成功", 200, String.valueOf(call));
    }

    @GetMapping("/shell3")
    public ResponesDto shell3() {
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        // 编写可执行的脚本
        ExecutableMongoScript findUserCountScript = new ExecutableMongoScript("function() {" +
                "var doc=db.user.count();" +
                "return doc;}");
        // 注册存储过程，指定脚本名称test2
        scriptOps.register(new NamedMongoScript("count", findUserCountScript));
        // 调用远程脚本，并设置参数
        Object findUser = scriptOps.call("count");
        String userString = JSONObject.toJSONString(findUser);
        System.out.println(userString);
        return ResponesDto.ok("操作成功", 200, userString);
    }
}
