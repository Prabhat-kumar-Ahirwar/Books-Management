package com.example.BookManagementApp.Service;

import com.example.BookManagementApp.Entity.Books;
import com.example.BookManagementApp.Repository.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepo bookRepo;


    public List<Books> getAllBooks() {
        return bookRepo.findAll();
    }

    public Books addNewBook(Books book) {
        return bookRepo.save(book);
    }

    public Books getBookById(long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public void remove(long id) {
            Books books = bookRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            bookRepo.delete(books);
        }
}
