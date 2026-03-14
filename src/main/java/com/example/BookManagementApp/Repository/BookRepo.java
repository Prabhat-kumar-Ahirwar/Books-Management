package com.example.BookManagementApp.Repository;

import com.example.BookManagementApp.Entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Books, Long> {

    Page<Books> findByNameContaining(String name, Pageable pageable);

    Page<Books> findByGenreContaining(String genre, Pageable pageable);

    Page<Books> findByAuthorContaining(String author, Pageable pageable);
}
