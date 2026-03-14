package com.example.BookManagementApp.Controller;

import com.example.BookManagementApp.Entity.Books;
import com.example.BookManagementApp.Exception.BookNotFoundException;
import com.example.BookManagementApp.Service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    // Get all books with pagination
    @GetMapping
    public ResponseEntity<Page<Books>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Books> books = bookService.getAllBooks(page, size);
        return ResponseEntity.ok(books);
    }

    // Add new book
    @PostMapping
    public ResponseEntity<Books> addBook(@Valid @RequestBody Books books) {
        Books book = bookService.addNewBook(books);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable long id) throws BookNotFoundException {

        Books book = bookService.getBookById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable long id) {

        bookService.remove(id);

        return ResponseEntity.noContent().build();
    }

    // Update book
    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(
            @PathVariable long id,
            @RequestBody Books book) {

        Books updatedBook = bookService.update(id, book);

        return ResponseEntity.ok(updatedBook);
    }

    // Search by name
    @GetMapping("/search/name/{name}")
    public ResponseEntity<Page<Books>> getBooksByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Books> books = bookService.getBookByName(name, page, size);

        return ResponseEntity.ok(books);
    }

    // Search by genre
    @GetMapping("/search/genre/{genre}")
    public ResponseEntity<Page<Books>> getBooksByGenre(
            @PathVariable String genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Books> books = bookService.getBookByGenre(genre, page, size);

        return ResponseEntity.ok(books);
    }

    // Search by author
    @GetMapping("/search/author/{author}")
    public ResponseEntity<Page<Books>> getBooksByAuthor(
            @PathVariable String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Books> books = bookService.getBookByAuthor(author, page, size);

        return ResponseEntity.ok(books);
    }

    // Filter by price range
    @GetMapping("/filter/price")
    public ResponseEntity<Page<Books>> getBooksByPriceRange(
            @RequestParam int minprice,
            @RequestParam int maxprice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Books> books = bookService.getBookByRange(minprice, maxprice, page, size);

        return ResponseEntity.ok(books);
    }
}