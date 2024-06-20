package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.CommunityMapper;
import com.example.reddit.mapper.ReactionMapper;
import com.example.reddit.mapper.ReportMapper;
import com.example.reddit.mapper.UserMapper;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Community;
import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;

@Controller
public class PostController {

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

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        List<Community> communities = communityService.findAll();
        List<CommunityDTO> communitiesDTO = new ArrayList<>();
        for (Community community : communities) {
            communitiesDTO.add(new CommunityDTO(community));
        }
        model.addAttribute("communities", communitiesDTO);
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Post post = postService.findOne(id);
        if (post == null) {
            return "error/404";
        }
        PostDTO postDTO = new PostDTO(post);
        model.addAttribute("post", postDTO);
        return "post";
    }

    private ArrayList<Post> PostDTOToModel(List<PostDTO> listDTO) {
        ArrayList<Post> list = new ArrayList<>();
        for (PostDTO objectDTO : listDTO) {
            list.add(postService.findOne(objectDTO.getId()));
        }
        return list;
    }

    private ArrayList<Ban> BanDTOToModel(List<BanDTO> listDTO) {
        ArrayList<Ban> list = new ArrayList<>();
        for (BanDTO objectDTO : listDTO) {
            list.add(banService.findOne(objectDTO.getId()));
        }
        return list;
    }

    private ArrayList<Comment> CommentDTOToModel(List<CommentDTO> listDTO) {
        ArrayList<Comment> list = new ArrayList<>();
        for (CommentDTO objectDTO : listDTO) {
            list.add(commentService.findOne(objectDTO.getId()));
        }
        return list;
    }

    private ArrayList<Reaction> ReactionDTOToModel(List<ReactionDTO> listDTO) {
        ArrayList<Reaction> list = new ArrayList<>();
        for (ReactionDTO objectDTO : listDTO) {
            list.add(reactionService.findOne(objectDTO.getId()));
        }
        return list;
    }

    private ArrayList<Report> ReportDTOToModel(List<ReportDTO> listDTO) {
        ArrayList<Report> list = new ArrayList<>();
        for (ReportDTO objectDTO : listDTO) {
            list.add(reportService.findOne(objectDTO.getId()));
        }
        return list;
    }

    @PostMapping("/posts")
    public String savePost(@RequestBody PostDTO postDTO, Model model) {
        Post post = new Post();
        post.setCommunity(communityService.findOne(postDTO.getCommunity().getId()));
        post.setCreationDate(postDTO.getCreationDate());
        post.setImagePath(postDTO.getImagePath());
        post.setText(postDTO.getText());
        post.setTitle(postDTO.getTitle());
        post.setUser(userService.findOne(postDTO.getUser().getId()));
        // post.setComments(CommentDTOToModel(postDTO.getComments()));
        // post.setReactions(ReactionDTOToModel(postDTO.getReactions()));
        // post.setReports(ReportDTOToModel(postDTO.getReports()));
        post = postService.save(post);
        return "redirect:/posts";
    }

    @PutMapping("/posts")
    public String updatePost(@RequestBody PostDTO postDTO, Model model) {
        Post post = postService.findOne(postDTO.getId());
        if (post == null) {
            return "error/400";
        }
        post.setCommunity(communityService.findOne(postDTO.getCommunity().getId()));
        post.setCreationDate(postDTO.getCreationDate());
        post.setImagePath(postDTO.getImagePath());
        post.setText(postDTO.getText());
        post.setTitle(postDTO.getTitle());
        post.setUser(userService.findOne(postDTO.getUser().getId()));
        // post.setComments(CommentDTOToModel(postDTO.getComments()));
        // post.setReactions(ReactionDTOToModel(postDTO.getReactions()));
        // post.setReports(ReportDTOToModel(postDTO.getReports()));
        post = postService.save(post);
        model.addAttribute("post", new PostDTO(post));
        return "post";
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        Post post = postService.findOne(id);
        if (post != null) {
            postService.remove(id);
            return "redirect:/posts";
        } else {
            return "error/404";
        }
    }

    private ArrayList<CommentDTO> getCommentsForPost(Long id) {
        Post post = postService.findOne(id);
        if (post == null || post.getComments() == null) {
            return new ArrayList<>();
        }
        ArrayList<CommentDTO> comments = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            comments.add(new CommentMapper().modelToDto(comment));
        }
        return comments;
    }

    private CommunityDTO getCommunityForPost(Long id) {
        Post post = postService.findOne(id);
        if (post == null || post.getCommunity() == null) {
            return null;
        }
        return new CommunityMapper().modelToDto(post.getCommunity());
    }

    private UserDTO getUserForPost(Long id) {
        Post post = postService.findOne(id);
        if (post == null || post.getUser() == null) {
            return null;
        }
        return new UserMapper().modelToDto(post.getUser());
    }

    private ArrayList<ReactionDTO> getReactionsForPost(Long id) {
        Post post = postService.findOne(id);
        if (post == null || post.getReactions() == null) {
            return new ArrayList<>();
        }
        ArrayList<ReactionDTO> reactions = new ArrayList<>();
        for (Reaction reaction : post.getReactions()) {
            reactions.add(new ReactionMapper().modelToDto(reaction));
        }
        return reactions;
    }

    private ArrayList<ReportDTO> getReportsForPost(Long id) {
        Post post = postService.findOne(id);
        if (post == null || post.getReports() == null) {
            return new ArrayList<>();
        }
        ArrayList<ReportDTO> reports = new ArrayList<>();
        for (Report report : post.getReports()) {
            reports.add(new ReportMapper().modelToDto(report));
        }
        return reports;
    }
}
