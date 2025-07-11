package com.example.bookcrud.service;

import com.example.bookcrud.entity.Book;
import com.example.bookcrud.exception.ResourceNotFoundException;
import com.example.bookcrud.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book("Test Title", "Test Author", "123-456789");
        book.setId(1L);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();
        assertEquals(1, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById_Present() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test Title", result.get().getTitle());
    }

    @Test
    void testGetBookById_NotPresent() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.getBookById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    void testCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book saved = bookService.createBook(book);
        assertEquals("Test Title", saved.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook_Success() {
        Book updatedData = new Book("Updated", "Author", "ISBN-001");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.updateBook(1L, updatedData);
        assertEquals("Updated", result.getTitle());
        verify(bookRepository).save(book);
    }

    @Test
    void testUpdateBook_NotFound() {
        Book updatedData = new Book("Updated", "Author", "ISBN-001");
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.updateBook(2L, updatedData));
    }

    @Test
    void testDeleteBook_Success() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBook_NotFound() {
        when(bookRepository.existsById(2L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(2L));
    }
}
