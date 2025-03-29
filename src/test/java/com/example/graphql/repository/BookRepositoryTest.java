package com.example.graphql.repository;


import com.example.graphql.model.Book;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest  // Load only JPA-related components for faster tests
//@Sql(scripts = "classpath:/schema.sql")  // Load table schema
//@Sql(scripts = "classpath:/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "classpath:/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        bookRepository.save(new Book("GraphQL for Beginners", "John Doe", 300));
        bookRepository.save(new Book("Spring Boot in Action", "Craig Walls", 400));
    }
    
    @Test
    public void testDatabaseShouldHaveRecords() {
        long count = bookRepository.count();
        assertEquals(2, count, "Data.sql did not load correctly!");
    }

    @Test
    public void testFindAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void testAddNewBook() {
        Book book = new Book("Java 11 Programming", "Mark Smith", 500);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertEquals(3, books.size());  // 2 from data.sql + 1 added
    }
}
