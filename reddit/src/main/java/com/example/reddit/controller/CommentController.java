package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.ReactionMapper;
import com.example.reddit.mapper.ReportMapper;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;
import com.example.reddit.model.User;
import com.example.reddit.service.*;

@Controller
public class CommentController {

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

    @GetMapping("/comments/all")
    public String getAllComments(Model model) {
        List<Comment> comments = commentService.findAll();
        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment obj : comments) {
            CommentDTO comment = new CommentDTO(obj);
            commentsDTO.add(comment);
        }
        model.addAttribute("comments", commentsDTO);
        return "comments";
    }

    @GetMapping("/comments/{id}")
    public String getComment(@PathVariable Long id, Model model) {
        Comment comment = commentService.findOne(id);
        if (comment == null) {
            return "commentNotFound";
        }
        CommentDTO commentDTO = new CommentDTO(comment);
        model.addAttribute("comment", commentDTO);
        return "comment";
    }
    
//    @GetMapping("/comments/{postId}")
//    public String getCommentsForPost(@PathVariable Long postId, Model model) {
//        List<Comment> comments = commentService.findByPostId(postId);
//        List<CommentDTO> commentsDTO = new ArrayList<>();
//        List<List<Reaction>> likesList = new ArrayList<>();
//        List<List<Reaction>> dislikesList = new ArrayList<>();
//
//        for (Comment comment : comments) {
//            CommentDTO commentDTO = new CommentDTO(comment);
//            commentsDTO.add(commentDTO);
//
//            List<Reaction> likes = new ArrayList<>();
//            List<Reaction> dislikes = new ArrayList<>();
//
//            for (Reaction reaction : comment.getReactions()) {
//                if ("type1".equals(reaction.getType())) {
//                    likes.add(reaction);
//                } else if ("type2".equals(reaction.getType())) {
//                    dislikes.add(reaction);
//                }
//            }
//
//            likesList.add(likes);
//            dislikesList.add(dislikes);
//        }
//
//        model.addAttribute("comments", commentsDTO);
//        model.addAttribute("likesList", likesList);
//        model.addAttribute("dislikesList", dislikesList);
//        return "comment"; // Replace with your actual view name
//    }


   


    public ArrayList<Comment> CommentDTOToModel(List<CommentDTO> listDTO) {
        ArrayList<Comment> list = new ArrayList<>();
        for (CommentDTO objectDTO : listDTO) {
            list.add(commentService.findOne(objectDTO.getId()));
        }
        return list;
    }

    public ArrayList<Reaction> ReactionDTOToModel(List<ReactionDTO> listDTO) {
        ArrayList<Reaction> list = new ArrayList<>();
        for (ReactionDTO objectDTO : listDTO) {
            list.add(reactionService.findOne(objectDTO.getId()));
        }
        return list;
    }

    public ArrayList<Report> ReportDTOToModel(List<ReportDTO> listDTO) {
        ArrayList<Report> list = new ArrayList<>();
        for (ReportDTO objectDTO : listDTO) {
            list.add(reportService.findOne(objectDTO.getId()));
        }
        return list;
    }

    @PostMapping("/comments/add/{postId}")
    public String saveComment(@PathVariable Long postId, @ModelAttribute CommentDTO commentDTO, Model model, HttpSession session) {
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setTimestamp(new Date()); // Assuming you want to set the current timestamp
        
        comment.setPost(postService.findOne(postId)); // Using postId from path variable
        //comment.setMainComment(new Comment());
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            // Handle case where user is not logged in
            return "redirect:/index";
        }
        comment.setUser(currentUser);

        comment = commentService.save(comment);
        model.addAttribute("comment", new CommentDTO(comment));
        return "redirect:/communities/details/" + comment.getPost().getCommunity().getId();
    }


    @PostMapping("/comments/edit")
    public String editComment(@ModelAttribute("commentDTO") CommentDTO commentDTO, HttpSession session) {
        // Retrieve the comment by its ID
    	System.out.println("hello");
        Comment comment = commentService.findOne(commentDTO.getId());
        if (comment == null) {
            return "redirect:/error";
        }
        comment.setText(commentDTO.getText());
        comment.setTimestamp(new Date());

        comment = commentService.save(comment);

        // Redirect to the details page of the community post after editing the comment
        return "redirect:/communities/details/" + comment.getPost().getCommunity().getId();
    }



    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id, Model model) {
        Comment comment = commentService.findOne(id);
        if (comment != null) {
            commentService.remove(id);
            return "commentDeleted";
        } else {
            return "commentNotFound";
        }
    }



    @GetMapping("/comments/reactions/{id}")
    public String getReactionsForComment(@PathVariable Long id, Model model) {
        Comment comment = commentService.findOne(id);
        if (comment == null || comment.getReactions() == null) {
            return "reactionsNotFound";
        }
        ArrayList<ReactionDTO> reactions = new ArrayList<>();
        for (Reaction reaction : comment.getReactions()) {
            reactions.add(new ReactionMapper().modelToDto(reaction));
        }
        model.addAttribute("reactions", reactions);
        return "viewReactions";
    }

    @GetMapping("/comments/reports/{id}")
    public String getReportsForComment(@PathVariable Long id, Model model) {
        Comment comment = commentService.findOne(id);
        if (comment == null || comment.getReports() == null) {
            return "reportsNotFound";
        }
        ArrayList<ReportDTO> reports = new ArrayList<>();
        for (Report report : comment.getReports()) {
            reports.add(new ReportMapper().modelToDto(report));
        }
        model.addAttribute("reports", reports);
        return "viewReports";
    }
}
