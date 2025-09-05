package com.Biblioteca_Virtual.repository;
import com.Biblioteca_Virtual.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
