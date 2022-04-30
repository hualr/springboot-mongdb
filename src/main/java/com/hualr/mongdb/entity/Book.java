package com.hualr.mongdb.entity;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * Function: 书籍<br>
 * Creating Time: 2022/4/30 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
@Data
@Accessors(chain = true)
@Document
public class Book {
 /*   @Id
    private String id;*/

    @MongoId
    private String mongoDbId;

    @Field("book_name")
    private String name;

    private Author author;

    @Data
    public static class Author {
        private String name;
        // 如果查询到是空 那么就是null
        private Sex sex=Sex.UNKNOWN;
        private List<Student> students;


        @Data
        @Accessors(chain = true)
        public static class Student {
            private String name;
            private Sex sex = Sex.UNKNOWN;
        }

        public enum Sex {
            MAN, WOMAN, UNKNOWN;
        }
    }
}
