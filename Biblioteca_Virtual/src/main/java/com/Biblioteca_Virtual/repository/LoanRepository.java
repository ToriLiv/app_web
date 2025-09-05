package com.Biblioteca_Virtual.repository;
import com.Biblioteca_Virtual.model.Book;
import com.Biblioteca_Virtual.model.Loan;
import com.Biblioteca_Virtual.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//--------------------REPOSITORY FOR LOANS---------------------->>
/*
 * This interface extends JpaRepository to provide CRUD operations for Loan entities.
 * It includes custom methods to find loans by book ID and status, check for active loans,
 * and retrieve all loans associated with a specific book.
 * The Loan entity is identified by a Long type ID.
 */

public interface LoanRepository extends JpaRepository <Loan, Long> {
    Loan findByBookIdAndStatus(Long bookId, LoanStatus status);
    Loan findFirstByUserIdAndStatus(Long userId, LoanStatus status);
    List<Loan> findByBook(Book book);

}
