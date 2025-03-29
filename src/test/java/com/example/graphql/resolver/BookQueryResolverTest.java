package com.example.graphql.resolver;

import com.example.graphql.model.Book;
import com.example.graphql.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookQueryResolverTest {

    @Mock
    private BookRepository bookRepository;  // Mock the repository

    @InjectMocks
    private BookQueryResolver bookQueryResolver;  // Inject the resolver

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book("GraphQL for Beginners", "John Doe", 300),
                new Book("Spring Boot in Action", "Craig Walls", 400)
        );

        when(bookRepository.findAll()).thenReturn(books); // Mock repository response

        List<Book> result = bookQueryResolver.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("GraphQL for Beginners", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();  // Verify method execution
    }

    @Test
    void testGetBookById() {
        Book book = new Book("GraphQL for Beginners", "John Doe", 300);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookQueryResolver.getBookById(1L);

        assertEquals("GraphQL for Beginners", result.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }
}
