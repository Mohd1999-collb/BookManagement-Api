package com.talibaccioJob.bookManagement;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
@Repository
/*Handled all data operation like get, post, put and delete*/
public class BookRepository {
    BookRepository(){
        System.out.println("Mai Book Repository ka constructor hu");
    }
    HashMap<Integer, Book> data = new HashMap<>();
    public Boolean add(Book book) {
        data.put(book.getBookId(), book);
        return true;
    }

    public Optional<Book> getById(int bookId) {
        if (data.containsKey(bookId)) {
            return Optional.of(data.get(bookId));
        }
        return Optional.empty();
    }

    public void removeBook(int id) {
        data.remove(id);
    }
}
