package com.Biblioteca_Virtual.controller;
import com.Biblioteca_Virtual.model.Loan;
import com.Biblioteca_Virtual.model.LoanStatus;
import com.Biblioteca_Virtual.model.User;
import com.Biblioteca_Virtual.repository.LoanRepository;
import com.Biblioteca_Virtual.repository.UserRepository;
import com.Biblioteca_Virtual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")

public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private LoanRepository loanRepository;

        //-----LIST USERS-----
        @GetMapping("/list")
        public String listUsers(Model model) {
            List<User> users = userService.list();

            //encuentra prestamos activos
            Map<Long, Loan> activeLoans = new HashMap<>();
            for (User u : users) {
                Loan activeLoan = loanRepository.findFirstByUserIdAndStatus(u.getId(), LoanStatus.ACTIVE);
                if (activeLoan != null) {
                    activeLoans.put(u.getId(), activeLoan);
                }
            }

            model.addAttribute("users", users);
            model.addAttribute("activeLoans", activeLoans);
            return "users";
        }


    //-----ADD USER-----
        @GetMapping("/adduser")
        public String addUser(Model model) {
            model.addAttribute("user", new User());
            return "adduser";
        }

        //-----SAVE USER-----
        @PostMapping("/saveuser")
        public String saveUser(@ModelAttribute User user) {
            userService.saveUser(user);
            return "redirect:/users/list";
        }

        //-----EDIT USER-----
        @GetMapping("/edituser")
        public String editUser(@RequestParam("id") Long id, Model model) {
            User user = userService.getUser(id);
            if (user != null) {
                model.addAttribute("user", user);
                return "edituser"; //template edit
            } else {
                return "redirect:/users/list";
            }
        }


    @GetMapping("/deleteuser")
    public String deleteBooks(@RequestParam("id") Long id) {
        userService.deleteUser(id);
            return "redirect:/users/list";
        }
    }


