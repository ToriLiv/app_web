package com.Biblioteca_Virtual.repository;
import com.Biblioteca_Virtual.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByDeletedFalse();
}
