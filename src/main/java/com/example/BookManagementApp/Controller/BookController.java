package com.example.BookManagementApp.Controller;

import com.example.BookManagementApp.Entity.Books;
import com.example.BookManagementApp.Service.BookService;
import lombok.AllArgsConstructor;

import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Books>> getAll() {
        List<Books> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Books> addBook(@RequestBody Books books) {
        Books book = bookService.addNewBook(books);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Books> getBookById(@PathVariable long id){
        Books book = bookService.getBookById(id);
        if(book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> remove(@PathVariable long id){
        bookService.remove(id);
        return ResponseEntity.noContent().build();
    }
}

