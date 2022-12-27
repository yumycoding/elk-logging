package com.yumy.elklogging;

import com.yumy.elklogging.dao.BookRepository;
import com.yumy.elklogging.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final BookRepository bookRepository;

    public Controller(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Domain Class Convertor Example
    // class called the Domain Class Convertor in Spring Data.
    // This class will allow you to take arbitrary input like an id
    // from a path variable in a REST API and automatically
    // create a domain object by using Spring Data's CrudRepository
    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") Book book) {
        return ResponseEntity.ok(book);
    }
}
