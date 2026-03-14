package com.example.BookManagementApp.Service;

import com.example.BookManagementApp.Entity.Books;
import com.example.BookManagementApp.Exception.BookNotFoundException;
import com.example.BookManagementApp.Repository.BookRepo;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    // Get all books with pagination
    public Page<Books> getAllBooks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bookRepo.findAll(pageable);
    }

    // Add new book
    public Books addNewBook(Books book) {

        return bookRepo.save(book);
    }

    // Get book by id
    public Books getBookById(long id) throws BookNotFoundException {

        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));
    }

    // Delete book
    public void remove(long id) {

        Books book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));

        bookRepo.delete(book);
    }

    // Update book
    public Books update(long id, Books updatedBook) {

        Books existingBook = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));

        existingBook.setName(updatedBook.getName());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setStock(updatedBook.getStock());
        existingBook.setPublishDate(updatedBook.getPublishDate());
        existingBook.setPublisher(updatedBook.getPublisher());

        return bookRepo.save(existingBook);
    }

    // Search by name
    public Page<Books> getBookByName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bookRepo.findByNameContaining(name, pageable);
    }

    // Search by genre
    public Page<Books> getBookByGenre(String genre, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bookRepo.findByGenreContaining(genre, pageable);
    }

    // Search by author
    public Page<Books> getBookByAuthor(String author, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bookRepo.findByAuthorContaining(author, pageable);
    }

    // Price range filter
    public Page<Books> getBookByRange(int minprice, int maxprice, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bookRepo.findByPriceBetween(minprice, maxprice, pageable);
    }
}