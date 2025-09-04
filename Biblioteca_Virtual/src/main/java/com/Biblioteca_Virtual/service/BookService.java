package com.Biblioteca_Virtual.service;

import com.Biblioteca_Virtual.model.Book;
import com.Biblioteca_Virtual.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    //---------------------service class for managing data--------------------->>

    /* This class provides methods to interact with the BookRepository.
     * It includes methods for listing, retrieving, saving, updating, and deleting books.
     * Bussiness logic.
     */

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list() {
        return bookRepository.findByDeletedFalse();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Book not found"));
        book.setDeleted(true);  //-------soft delete
        bookRepository.save(book);
    }
}
