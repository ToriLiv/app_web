package com.Biblioteca_Virtual.repository;

import com.Biblioteca_Virtual.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//-------------------database repository for Book entity------------------->>

/*
 * This interface extends JpaRepository to provide CRUD operations for Book entities.
 * It allows us to perform operations like saving, deleting, and finding books in the database.
 * The Book entity is identified by a Long type ID.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByDeletedFalse();
}
