package com.example.reddit.controller;

import com.example.reddit.dto.*;
import com.example.reddit.mapper.*;
import com.example.reddit.model.*;
import com.example.reddit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BanService banService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private PostService postService;
    @Autowired
    private ReactionService reactionService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @PostMapping("/users/login")
    public String loginUser(@RequestBody UserDTO userDTO, HttpSession session) {
        User user = userService.findByUsername(userDTO.getUsername());
        if (user == null || !user.getPassword().equals(userDTO.getPassword())) {
            
            return "redirect:/index";
        }

        session.setAttribute("currentUser", user);

        return "redirect:/communities";
    }

    @PostMapping("/users/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user));
        }
        model.addAttribute("users", usersDTO);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findOne(id);
        if (user == null) {
            // Handle not found scenario
            return "error";
        }

        UserDTO userDTO = new UserDTO(user);
        model.addAttribute("user", userDTO);
        return "user";
    }
    
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Assuming "register.html" is in your templates directory
    }

    @PostMapping("/users/register")
    public String saveUser(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam String displayName,
                           @RequestParam String description) {
        if (!password.equals(confirmPassword)) {
            // Handle password mismatch
            //return "redirect:/register";
        }
        System.out.println("mlem");

        User user = new User();
        user.setAvatar("avatar");
        user.setDescription(description);
        user.setDisplayName(displayName);
        user.setPassword(password);
        user.setRegistrationDate(new Date());
        user.setRole("ROLE_USER");
        user.setUsername(username);

        user = userService.save(user);
        return "redirect:/"; 
    }
    
    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam(value = "password", required = false) String newPassword,
                             Model model, HttpSession session) {
    	User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            // Handle case where user is not logged in
            return "redirect:/index";
        }
    	
        User existingUser = userService.findOne(currentUser.getId());
        existingUser.setDescription(user.getDescription());
        existingUser.setDisplayName(user.getDisplayName());
        existingUser.setUsername(user.getUsername());
        if (newPassword != null && !newPassword.isEmpty()) {
            existingUser.setPassword(newPassword);
        }
        userService.save(existingUser);
        return "redirect:/users/" + existingUser.getId();
    }



    @PutMapping(consumes="application/json")
    public String updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.findOne(userDTO.getId());
        if (user == null) {
            // Handle not found scenario
            return "error";
        }

        // Update user fields from userDTO
        user = userService.save(user);
        return "redirect:/api/users/" + user.getId();
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user == null) {
            // Handle not found scenario
            return "error";
        }

        userService.remove(id);
        return "redirect:/api/users/all";
    }

  
}
