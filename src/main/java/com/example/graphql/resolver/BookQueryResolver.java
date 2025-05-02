package com.example.graphql.resolver;

import com.example.graphql.model.Book;
import com.example.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BookQueryResolver {

    @Autowired
    private BookRepository bookRepository;


    @QueryMapping  // Replaces GraphQLQueryResolver
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    @QueryMapping  // Replaces GraphQLQueryResolver
    public Optional<Book> getBookById(Long id) {

        return bookRepository.findById(id);
    }
}
