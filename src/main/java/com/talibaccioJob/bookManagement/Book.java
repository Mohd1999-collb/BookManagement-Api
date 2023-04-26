package com.talibaccioJob.bookManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int bookId;
    private String title;
    private String author;
    private Integer pages;

    public Book(int bookId, String title, String author, Integer pages) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
}
