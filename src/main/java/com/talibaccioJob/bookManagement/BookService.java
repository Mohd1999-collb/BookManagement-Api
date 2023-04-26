package com.talibaccioJob.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service()
/*Provide all the services*/
public class BookService {
    @Autowired
    BookRepository bookRepository;
//    BookRepository bookRepository = new BookRepository();
    BookService(){
        System.out.println("Mai Book Service ka constructor hu");
    }

    /*Book Add service*/
    public Boolean addBook(Book book) throws BookAlreadyExistsException{
        /*If book is already exist in database then return exception*/
        Optional<Book> bookOpt = bookRepository.getById(book.getBookId());
        if (bookOpt.isPresent()) {
            throw new BookAlreadyExistsException(book.getBookId());
        }
        return bookRepository.add(book);
    }

    /*Get book service*/
    public Book getBook(int id) {
        Optional<Book> bookOpt = bookRepository.getById(id);
        if (bookOpt.isEmpty()) {
            throw new BookNotFoundException(id);
        }
        return bookOpt.get();
    }

    /*Book update service*/
    public String bookUpdate(int bookId, String title, String author, Integer pages) {
        try{
            Book book = getBook(bookId);
            if (Objects.nonNull(title)) {
                book.setTitle(title);
            }
            if (Objects.nonNull(author)) {
                book.setAuthor((author));
            }
            if (Objects.nonNull(pages)) {
                book.setPages(pages);
            }
            bookRepository.add(book);
            return "Book Updated";
        }catch (BookNotFoundException ex){
            Book book = new Book(bookId, title, author, pages);
            bookRepository.add(book);
            return "New book is created of id " + bookId + " because book is not exist in database.";
        }
    }

    /*Book remove service*/
    public Boolean remove(int id) {
        Optional<Book> bookOpt = bookRepository.getById(id);
        if (bookOpt.isEmpty()) {
            throw new BookNotFoundException(id);
        }
        bookRepository.removeBook(id);
        return true;
    }
}
