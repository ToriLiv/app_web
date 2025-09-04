package com.Biblioteca_Virtual.controller;
import com.Biblioteca_Virtual.model.Book;
import com.Biblioteca_Virtual.model.BookStatus;
import com.Biblioteca_Virtual.model.Loan;
import com.Biblioteca_Virtual.model.LoanStatus;
import com.Biblioteca_Virtual.repository.BookRepository;
import com.Biblioteca_Virtual.repository.LoanRepository;
import com.Biblioteca_Virtual.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    //-----------create loan------------------>
    @PostMapping("/create/{id}")
    public String createLoan(@PathVariable Long id, @RequestParam String borrower) {
        Book book = bookRepository.findById(id).orElseThrow();
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBorrowerName(borrower);
        loan.setLoanDate(LocalDate.now());
        loan.setStatus(LoanStatus.ACTIVE);
        loanRepository.save(loan);

        //update book status to borrowed
        book.setStatus(BookStatus.BORROWED);
        bookRepository.save(book);
        return "redirect:/books/list";
    }

    //-----------return book------------------>
    @PostMapping("/return/{loanId}")
    public String returnLoan(@PathVariable Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        //update book status to available
        Book book = loan.getBook();
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
        return "redirect:/books/list";
    }

    //------------------loan form--------------------->>
    @GetMapping("/book/{id}")
    public String loanForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book", book);
        model.addAttribute("loan", new Loan());
        return "create";

    }

    //------------------list loans--------------------->>
    @GetMapping("/list")
    public String listLoans(Model model) {
        List<Loan> loans = loanRepository.findAll();
        model.addAttribute("loans", loans);
        return "loans";
    }
}
