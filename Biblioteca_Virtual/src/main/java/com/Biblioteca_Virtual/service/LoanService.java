package com.Biblioteca_Virtual.service;

import com.Biblioteca_Virtual.model.*;
import com.Biblioteca_Virtual.repository.BookRepository;
import com.Biblioteca_Virtual.repository.LoanRepository;
import com.Biblioteca_Virtual.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

//service class for managin loans
//business logic for creating and returning loans

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;



    //--------------manage loans---------------------->>
    public Loan createLoan(Book book, User user) {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(2)); //2 weeks loan period
        loan.setStatus(LoanStatus.ACTIVE);
        book.setStatus(BookStatus.BORROWED);
        bookRepository.save(book);
        return loanRepository.save(loan);
    }

    public Loan returnBook(Loan loan) {
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);
        Book book = loan.getBook();
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
        return loanRepository.save(loan);
    }
}

