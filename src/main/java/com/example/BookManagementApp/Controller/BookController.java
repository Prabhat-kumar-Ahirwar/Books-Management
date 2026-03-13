package com.example.BookManagementApp.Controller;

import com.example.BookManagementApp.Entity.Books;
import com.example.BookManagementApp.Exception.BookNotFoundException;
import com.example.BookManagementApp.Service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import lombok.Getter;
import org.apache.coyote.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
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
    public ResponseEntity<Page<Books>> getAll(@RequestParam int page, @RequestParam int size) {
        Page<Books> books = bookService.getAllBooks(page, size);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Books> addBook(@Valid @RequestBody Books books) {
        Books book = bookService.addNewBook(books);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Books> getBookById(@PathVariable long id) throws BookNotFoundException {
        Books book = bookService.getBookById(id);
        if(book == null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> remove(@PathVariable long id){
        bookService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/search/id/{id}")
    public ResponseEntity<Books> update(@PathVariable long id,@RequestBody Books book) {
        Books updatedBook = bookService.update(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<Page<Books>> getBooksByName(@PathVariable String name , @RequestParam int page , @RequestParam int size){
        Page Books = bookService.getBookByName(name,page,size);
        return new ResponseEntity<>(Books,HttpStatus.OK);
    }
}

