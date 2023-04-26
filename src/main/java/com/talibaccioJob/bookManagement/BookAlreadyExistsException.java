package com.talibaccioJob.bookManagement;

public class BookAlreadyExistsException extends RuntimeException{
    BookAlreadyExistsException(int id){
        super("Book for id: " + id + " is already exists in database.");
    }
}
