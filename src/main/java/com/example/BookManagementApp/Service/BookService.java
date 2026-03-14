package com.example.BookManagementApp.Service;

import com.example.BookManagementApp.Entity.Books;
import com.example.BookManagementApp.Exception.BookNotFoundException;
import com.example.BookManagementApp.Repository.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepo bookRepo;


    public Page<Books> getAllBooks(int page , int size) {
        Pageable pageable = PageRequest.of(page,size);
        return bookRepo.findAll(pageable);
    }

    public Books addNewBook(Books book) {
        return bookRepo.save(book);
    }

    public Books getBookById(long id) throws BookNotFoundException{
        return bookRepo.findById(id).orElseThrow(()-> new BookNotFoundException("Book Not Found"));
    }

    public void remove(long id) {
            Books books = bookRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            bookRepo.delete(books);
        }
    public Books update(long id, Books updatedBook) {
        Books existingBook = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        existingBook.setName(updatedBook.getName());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());

        return bookRepo.save(existingBook);
    }

    public Page<Books> getBookByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return bookRepo.findByNameContaining(name,pageable);
    }

    public Page getBookByGenre(String genre, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return bookRepo.findByGenreContaining(genre,pageable);
    }

    public Page getBookByAuthor(String author, int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        return bookRepo.findByAuthorContaining(author,pageable);
    }
}
