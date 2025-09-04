package com.Biblioteca_Virtual.controller;
import com.Biblioteca_Virtual.model.*;
import com.Biblioteca_Virtual.repository.LoanRepository;
import com.Biblioteca_Virtual.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//-----------------------controller class for managing books--------------------->>

/*
 * This class handles HTTP requests related to book management.
 * It provides endpoints for listing, adding, editing, deleting, and saving books.
 * The methods interact with the BookService to perform operations on the Book entity.
 */

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookservice;

    @Autowired
    private LoanRepository loanRepository;

    //********************CRUD Operations for Books*********************

    //method to list all books
    @GetMapping("/list")
    public String listBooks(Model model) {
        List<Book> books = bookservice.list();
        model.addAttribute("books", books);

        Map<Long, Loan> activeLoans = new HashMap<>();
        for (Book book : books) {
            if (book.getStatus() == BookStatus.BORROWED) {
                Loan loan = loanRepository.findByBookIdAndStatus(book.getId(), LoanStatus.ACTIVE);
                if (loan != null) {
                    activeLoans.put(book.getId(), loan);
                }
            }
        }
        model.addAttribute("activeLoans", activeLoans);
        return "index";
    }

    //---------------------method to add books---------------------
    @GetMapping("/add")
    public String addBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("statuses", BookStatus.values());
        model.addAttribute("valuations", Valuation.values());
        return "add"; //template add
    }

    //---------------------method to edit books---------------------
    @GetMapping("/edit")
    public String editBooks(@RequestParam("id") Long id, Model model) {
        Book book = bookservice.getBook(id);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("statuses", BookStatus.values());
            model.addAttribute("valuations", Valuation.values());
            return "edit"; //template edit
        } else {
            return "redirect:/books/list";
        }
    }

    //---------------------method to delete books---------------------
    @GetMapping("/delete")
    public String deleteBooks(@RequestParam("id") Long id) {
        bookservice.deleteBook(id);
        return "redirect:/books/list";
    }

    //---------------------method to save books---------------------
    @PostMapping("/save")
    public String saveBooks(@ModelAttribute("book") Book book) {
        bookservice.saveBook(book);
        return "redirect:/books/list";
    }
}
