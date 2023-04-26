package com.talibaccioJob.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
//    BookService bookService = new BookService();
    BookController(){
        System.out.println("Mai book controller ka constructor hu");
    }

    /*Post request --> localhost:8080/add-book */
    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        try{
            Boolean added = bookService.addBook(book);
            return new ResponseEntity<>("Book Added successfully of id " + book.getBookId(), HttpStatus.CREATED);
        }catch (BookAlreadyExistsException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(400));
        } catch(Exception ex) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.valueOf(500));
        }
    }

    /*Add the book into the database using passing the parameter*/
//    public ResponseEntity<String> addBook(@RequestParam  int bookId, @RequestParam String title,
//                          @RequestParam String author, @RequestParam Integer pages){
//        Book book = new Book(bookId, title, author, pages);
//        try{
//            Boolean added = bookService.addBook(book);
//            return new ResponseEntity<>("Book Added successfully.", HttpStatus.CREATED); //200
//        }catch (BookAlreadyExistsException ex){
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(400));
//        }
//    }

    /*Get Request --> localhost:8080/get-book?id=3*/
    @GetMapping("/get-book")
    public ResponseEntity<String> findBook(@RequestParam int id){
        try{
            Book book = bookService.getBook(id);
            return new ResponseEntity(book, HttpStatus.OK); //200
        }catch (BookNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(400));
        } catch(Exception ex) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.valueOf(500));
        }
    }

    /*Get Request using pathVariable --> localhost:8080/get-book/3 where 3 is the book id*/
//    @GetMapping("get-book/{id}")
//    public ResponseEntity<String> findBook(@PathVariable int id){
//        try{
//            Book book = bookService.getBook(id);
//            return new ResponseEntity(book, HttpStatus.OK); //200
//        }catch (BookNotFoundException ex){
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(400));
//        }
//    }

    /*Update request --> localhost:8080*/
    @PutMapping("/update-book")
    public String updateBook(@RequestParam  int bookId, @RequestParam(defaultValue = "Mai Default title huu", required = false) String title, @RequestParam String author, @RequestParam Integer pages){
        return bookService.bookUpdate(bookId, title, author, pages);

    }

    /*Delete Request --> localhost:8080/delete-book?id=3*/
    @DeleteMapping("delete-book")
    public ResponseEntity<String> deleteBook(@RequestParam int id){
        try{
            boolean bool = bookService.remove(id);
            return new ResponseEntity<>("Book Removed of id " + id + " is Successfully", HttpStatus.OK);
        }catch (BookNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /*Delete Request using pathVariable --> localhost:8080/delete-book/2 where 2 is the book id*/
//    @DeleteMapping("delete-book/{id}")
//    public ResponseEntity<String> deleteBook(@PathVariable int id){
//        try{
//            bookService.remove(id);
//            return new ResponseEntity<>("Book Removed of id " + id + " is Successfully", HttpStatus.OK);
//        }catch (BookNotFoundException ex){
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
}
