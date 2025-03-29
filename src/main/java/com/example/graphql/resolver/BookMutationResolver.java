package com.example.graphql.resolver;

import com.example.graphql.model.Book;
import com.example.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookMutationResolver {

    @Autowired
    private final BookRepository bookRepository;

    public BookMutationResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(String title, String author, int pages) {
        Book book = new Book(title, author, pages);
        return bookRepository.save(book);
    }
}
