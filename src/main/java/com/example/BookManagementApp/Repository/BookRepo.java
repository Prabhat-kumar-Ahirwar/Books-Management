package com.example.BookManagementApp.Repository;

import com.example.BookManagementApp.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Books, Long> {
}
