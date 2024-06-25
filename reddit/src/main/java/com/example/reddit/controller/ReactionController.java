package com.example.reddit.controller;

import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.PostMapper;
import com.example.reddit.mapper.UserMapper;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.User;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

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

//    @GetMapping("/reactions/all")
//    public String getAllReactions(Model model) {
//        List<Reaction> reactions = reactionService.findAll();
//        List<ReactionDTO> reactionsDTO = new ArrayList<>();
//        for (Reaction reaction : reactions) {
//            reactionsDTO.add(new ReactionDTO(reaction));
//        }
//        model.addAttribute("reactions", reactionsDTO);
//        return "reactions/all";
//    }

    @GetMapping("/reactions/get/{id}")
    public String getReaction(@PathVariable Long id, Model model) {
        Reaction reaction = reactionService.findOne(id);
        if (reaction == null) {
            return "error";
        }
        model.addAttribute("reaction", new ReactionDTO(reaction));
        return "reactions/detail";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReaction(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        try {
            Integer postId = (Integer) requestMap.get("postId");
            Integer commentId = (Integer) requestMap.get("commentId");
            String type = (String) requestMap.get("type");

            // Check for null values
            if (postId == null || commentId == null || type == null) {
                return ResponseEntity.badRequest().body("Missing required parameters");
            }

            // Logging to verify received parameters
            System.out.println("Received parameters - postId: " + postId + ", commentId: " + commentId + ", type: " + type);

            // Your existing logic to handle adding reaction
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }

            Reaction reaction = new Reaction();
            reaction.setType(type);
            reaction.setUser(currentUser);
            reaction.setPost(postService.findOne((long) postId));  // Convert int to long if necessary
            reaction.setComment(commentService.findOne((long) commentId));  // Convert int to long if necessary
            reaction.setTimestamp(new Date());

            reactionService.save(reaction);

            System.out.println("Reaction added successfully");
            return ResponseEntity.ok("Reaction added successfully");
        } catch (Exception e) {
            System.err.println("Failed to add reaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add reaction");
        }
    }




    @PutMapping("/reactions/update")
    public String updateReaction(@ModelAttribute ReactionDTO reactionDTO) {
        Reaction reaction = reactionService.findOne(reactionDTO.getId());
        if (reaction == null) {
            return "error";
        }

        reaction.setTimestamp(reactionDTO.getTimestamp());
        reaction.setType(reactionDTO.getType());
        reaction.setComment(commentService.findOne(reactionDTO.getComment().getId()));
        reaction.setPost(postService.findOne(reactionDTO.getPost().getId()));
        reaction.setUser(userService.findOne(reactionDTO.getUser().getId()));

        reaction = reactionService.save(reaction);
        return "redirect:/reactions/all";
    }

    @DeleteMapping("/reactions/delete/{id}")
    public String deleteReaction(@PathVariable Long id) {
        Reaction reaction = reactionService.findOne(id);
        if (reaction != null) {
            reactionService.remove(id);
        }
        return "redirect:/reactions/all";
    }


}
