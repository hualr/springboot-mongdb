package com.hualr.mongdb.controller;

import com.hualr.mongdb.entity.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function: bookController<br>
 * Creating Time: 2022/4/30 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/save")
    public Book saveBook() {
        Book book1 = new Book().setName("三字经").setAuthor(
                new Book.Author()
                        .setSex(Book.Author.Sex.MAN)
                        .setName("太师")
                        .setStudents(new ArrayList<>())
        );


        Book.Author.Student student = new Book.Author.Student().setName("杨玉环").setSex(Book.Author.Sex.WOMAN);
        Book book2 = new Book().setName("唐诗").setAuthor(
                new Book.Author()
                        .setSex(Book.Author.Sex.MAN)
                        .setStudents(Collections.singletonList(student))
                        .setName("李白")
        );


        Book book3 = new Book().setName("匿名").setAuthor(
                new Book.Author()
                        .setName("诗经")
        );

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        mongoTemplate.insertAll(books);
        return book1;
    }

    @GetMapping("/find")
    public List<Book> find(@RequestParam String name) {
        Query query = new Query(Criteria.where("name").is(name));
        List<Book> books = mongoTemplate.find(query, Book.class);

        final List<Book.Author.Student> students = books.stream()
                .map(Book::getAuthor)
                .map(Book.Author::getStudents)
                .map(o -> (List<Book.Author.Student>) o)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(students);
        return books;
    }

    @GetMapping("/findAll")
    public List<Book> findAll() {
        return mongoTemplate.findAll(Book.class);
    }


}
