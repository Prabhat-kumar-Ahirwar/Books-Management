package com.example.BookManagementApp.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Author is required")
    private String author;

    @Positive(message = "Price must be more than 0")
    private double price;

    @Min(value = 0, message = "Stocks can not be negative")
    private int stock;
    private LocalDate publishDate;
    @NotBlank(message = "Publisher is required")
    private String publisher;
}