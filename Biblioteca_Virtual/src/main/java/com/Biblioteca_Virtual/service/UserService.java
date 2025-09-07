package com.Biblioteca_Virtual.service;
import com.Biblioteca_Virtual.model.Book;
import com.Biblioteca_Virtual.model.User;
import com.Biblioteca_Virtual.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//---------------------service class for managing data--------------------->>
/* This class provides methods to interact with the UserRepository.
 * It includes methods for listing, retrieving, saving, updating, and deleting users.
 * Business logic.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list() { return userRepository.findByDeletedFalse(); }

    public User getUser(Long id) { return userRepository.findById(id).orElse(null); }

    public void saveUser(User user) { userRepository.save(user); }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        user.setDeleted(true);  //-------soft delete
        userRepository.save(user);
    }
}
