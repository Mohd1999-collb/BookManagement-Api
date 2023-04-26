package com.talibaccioJob.bookManagement;

public class BookNotFoundException extends RuntimeException{
    BookNotFoundException(int id){
        super("Book does not exist for id : " + id);
    }
}
