package com.yumy.elklogging.entity;


import org.springframework.data.annotation.Id;

public record Book(@Id Integer id, String book_name, Integer pages, Double price, String author, String iban_number) {
}
