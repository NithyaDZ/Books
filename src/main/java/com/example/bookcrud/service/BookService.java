package com.example.bookcrud.service;

import com.example.bookcrud.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<Book> searchBooks(String keyword);

}
