package com.example.reddit.controller;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.PostMapper;
import com.example.reddit.mapper.UserMapper;
import com.example.reddit.model.Reaction;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
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

    @GetMapping("/reactions/all")
    public String getAllReactions(Model model) {
        List<Reaction> reactions = reactionService.findAll();
        List<ReactionDTO> reactionsDTO = new ArrayList<>();
        for (Reaction reaction : reactions) {
            reactionsDTO.add(new ReactionDTO(reaction));
        }
        model.addAttribute("reactions", reactionsDTO);
        return "reactions/all";
    }

    @GetMapping("/reactions/{id}")
    public String getReaction(@PathVariable Long id, Model model) {
        Reaction reaction = reactionService.findOne(id);
        if (reaction == null) {
            return "error";
        }
        model.addAttribute("reaction", new ReactionDTO(reaction));
        return "reactions/detail";
    }

    @PostMapping("/reactions")
    public String saveReaction(@ModelAttribute ReactionDTO reactionDTO) {
        Reaction reaction = new Reaction();
        reaction.setTimestamp(reactionDTO.getTimestamp());
        reaction.setType(reactionDTO.getType());
        reaction.setComment(commentService.findOne(reactionDTO.getComment().getId()));
        reaction.setPost(postService.findOne(reactionDTO.getPost().getId()));
        reaction.setUser(userService.findOne(reactionDTO.getUser().getId()));

        reaction = reactionService.save(reaction);
        return "redirect:/reactions/all";
    }

    @PutMapping("/reactions")
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

    @DeleteMapping("/reactions/{id}")
    public String deleteReaction(@PathVariable Long id) {
        Reaction reaction = reactionService.findOne(id);
        if (reaction != null) {
            reactionService.remove(id);
        }
        return "redirect:/reactions/all";
    }

    @ModelAttribute("comments")
    public List<CommentDTO> getCommentsForReaction(@RequestParam Long id) {
        Reaction reaction = reactionService.findOne(id);
        if (reaction == null || reaction.getComment() == null) {
            return new ArrayList<>();
        }

        ArrayList<CommentDTO> comments = new ArrayList<>();
        comments.add(new CommentMapper().modelToDto(reaction.getComment()));
        return comments;
    }

    @ModelAttribute("post")
    public PostDTO getPostForReaction(@RequestParam Long id) {
        Reaction reaction = reactionService.findOne(id);
        if (reaction == null || reaction.getPost() == null) {
            return null;
        }
        return new PostMapper().modelToDto(reaction.getPost());
    }

    @ModelAttribute("user")
    public UserDTO getUserForReaction(@RequestParam Long id) {
        Reaction reaction = reactionService.findOne(id);
        if (reaction == null || reaction.getUser() == null) {
            return null;
        }
        return new UserMapper().modelToDto(reaction.getUser());
    }
}
