package com.example.graphql.resolver;

import com.example.graphql.model.Book;
import com.example.graphql.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

//@SpringBootTest
@GraphQlTest(BookQueryResolver.class)
class BookQueryResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        bookRepository.save(new Book("GraphQL for Beginners", "John Doe", 300));
        bookRepository.save(new Book("Spring Boot in Action", "Craig Walls", 400));
    }

    @Test
    void testGetUsers() {
        String query = "{\n" +
                "                getAllBooks {\n" +
                "                    firstName\n" +
                "                    email\n" +
                "                }\n" +
                "            }";

        graphQlTester.document(query)
                .execute()
                .path("getUsers")
                .entityList(Book.class)
                .hasSize(2)
                .contains(new Book() {{
                    setTitle("GraphQL for Beginners");
                    setAuthor("John Doe");
                    setPages(300);
                }}, new Book() {{
                    setTitle("Spring Boot in Action");
                    setAuthor("Craig Walls");
                    setPages(400);
                }});
    }

    /*@Test
    void testGetAllBooks() {
        final List<Book> result = bookQueryResolver.getAllBooks();

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
    }*/
}
