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
            // Handle invalid login
            return "redirect:/login?error";
        }

        // Valid login, store user in session
        session.setAttribute("currentUser", user);

        // Redirect to listCommunities.html
        System.out.println("blah");
        return "redirect:/communities";
    }

    @RequestMapping(value="/logout", method=RequestMethod.POST)
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
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

    @GetMapping("/{id}")
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

    @PostMapping(consumes="application/json")
    public String saveUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        // Populate user object from userDTO
        user = userService.save(user);
        return "redirect:/api/users/" + user.getId();
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

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user == null) {
            // Handle not found scenario
            return "error";
        }

        userService.remove(id);
        return "redirect:/api/users/all";
    }

    // Methods to retrieve related entities

    @GetMapping("/{id}/bans")
    public String getBannedForUser(@PathVariable Long id, Model model) {
        List<BanDTO> bansDTO = getBannedDTOs(id); // Assume a method to retrieve BanDTOs for user
        model.addAttribute("bans", bansDTO);
        return "bans";
    }

    @GetMapping("/{id}/comments")
    public String getCommentsForUser(@PathVariable Long id, Model model) {
        List<CommentDTO> commentsDTO = getCommentsDTOs(id); // Assume a method to retrieve CommentDTOs for user
        model.addAttribute("comments", commentsDTO);
        return "comments";
    }

    @GetMapping("/{id}/communities")
    public String getCommunitiesForUser(@PathVariable Long id, Model model) {
        List<CommunityDTO> communitiesDTO = getCommunitiesDTOs(id); // Assume a method to retrieve CommunityDTOs for user
        model.addAttribute("communities", communitiesDTO);
        return "communities";
    }

    @GetMapping("/{id}/reactions")
    public String getReactionsForUser(@PathVariable Long id, Model model) {
        List<ReactionDTO> reactionsDTO = getReactionsDTOs(id); // Assume a method to retrieve ReactionDTOs for user
        model.addAttribute("reactions", reactionsDTO);
        return "reactions";
    }

    @GetMapping("/{id}/posts")
    public String getPostsForUser(@PathVariable Long id, Model model) {
        List<PostDTO> postsDTO = getPostsDTOs(id); // Assume a method to retrieve PostDTOs for user
        model.addAttribute("posts", postsDTO);
        return "posts";
    }

    // Helper methods to convert DTOs to models
    // These methods are similar to the ones you previously had for converting DTOs to entity models

    private List<BanDTO> getBannedDTOs(Long id) {
        // Implement logic to retrieve BanDTOs for user with given id
        return new ArrayList<>(); // Placeholder implementation
    }

    private List<CommentDTO> getCommentsDTOs(Long id) {
        // Implement logic to retrieve CommentDTOs for user with given id
        return new ArrayList<>(); // Placeholder implementation
    }

    private List<CommunityDTO> getCommunitiesDTOs(Long id) {
        // Implement logic to retrieve CommunityDTOs for user with given id
        return new ArrayList<>(); // Placeholder implementation
    }

    private List<ReactionDTO> getReactionsDTOs(Long id) {
        // Implement logic to retrieve ReactionDTOs for user with given id
        return new ArrayList<>(); // Placeholder implementation
    }

    private List<PostDTO> getPostsDTOs(Long id) {
        // Implement logic to retrieve PostDTOs for user with given id
        return new ArrayList<>(); // Placeholder implementation
    }
}
