package com.Biblioteca_Virtual;

import com.Biblioteca_Virtual.model.Book;
import com.Biblioteca_Virtual.model.BookStatus;
import com.Biblioteca_Virtual.model.Valuation;
import com.Biblioteca_Virtual.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//-------------------data loader for initial book data------------------->>
@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            Book b1 = new Book();
            b1.setTitle("The summer I turned Pretty");
            b1.setAuthor("Jenny Han");
            b1.setPublication_year(2009);
            b1.setCategory("Romance");
            b1.setStatus(BookStatus.AVAILABLE);
            b1.setValuation(Valuation.GOOD);
            b1.setDeleted(false);


            Book b2 = new Book();
            b2.setTitle("Twisted Love");
            b2.setAuthor("Ana Huang");
            b2.setPublication_year(2021);
            b2.setCategory("Romance");
            b2.setStatus(BookStatus.AVAILABLE);
            b2.setValuation(Valuation.FAIR);
            b2.setDeleted(false);

            bookRepository.save(b1);
            bookRepository.save(b2);
        }
    }
}
