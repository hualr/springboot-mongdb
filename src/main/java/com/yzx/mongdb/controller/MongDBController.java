package com.yzx.mongdb.controller;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.yzx.mongdb.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * mongodb 基本使用
 */
@Controller
@RequestMapping("/com/yzx/mongdb")
public class MongDBController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public String testMongo(){
        List<User> list = mongoTemplate.findAll(User.class);
        System.out.println("list==="+list);
        return "end";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(){
        User user = new User();
        user.setAge("15");
        user.setName("张三");
        user.setTel("1782828282");
        User user1 = mongoTemplate.save(user);
        return "save end";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(){
        Query query = new Query(Criteria.where("name").is("张三"));
        User user1 = mongoTemplate.findOne(query,User.class);
        return "query end";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(){
        Query query = new Query(Criteria.where("name").is("张三"));
        mongoTemplate.remove(query,User.class);
        return "remove  end";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(){
        Query query = new Query(Criteria.where("name").is("zhangsan5"));
        Update update = new Update();
        update.set("age","55");
        mongoTemplate.updateFirst(query,update,User.class);
        return "update  end";
    }

    @RequestMapping("/ex")
    @ResponseBody
    public String ex(){
        int i = 1/0;
        String str = null;
        str.toUpperCase();
        return "ex  end";
    }
}
