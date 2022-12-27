package com.yumy.elklogging.dao;

import com.yumy.elklogging.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
