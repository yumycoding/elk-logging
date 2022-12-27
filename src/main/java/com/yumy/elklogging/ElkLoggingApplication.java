package com.yumy.elklogging;

import com.yumy.elklogging.dao.BookRepository;
import com.yumy.elklogging.entity.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class ElkLoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElkLoggingApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            repository.save(new Book(null,"Spring Boot: Up and Running".toUpperCase(),500,328.59,"Mark Heckler".toUpperCase(),"IBAN-55486654"));
        };
    }

}
