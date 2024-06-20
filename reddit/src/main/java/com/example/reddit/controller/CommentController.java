package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.List;

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
        return "listComments";
    }

    @GetMapping("/comments/{id}")
    public String getComment(@PathVariable Long id, Model model) {
        Comment comment = commentService.findOne(id);
        if (comment == null) {
            return "commentNotFound";
        }
        CommentDTO commentDTO = new CommentDTO(comment);
        model.addAttribute("comment", commentDTO);
        return "viewComment";
    }

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

    @PostMapping("/comments")
    public String saveComment(@RequestBody CommentDTO commentDTO, Model model) {
        Comment comment = new Comment();
        comment.setIsDeleted(commentDTO.getIsDeleted());
        comment.setText(commentDTO.getText());
        comment.setTimestamp(commentDTO.getTimestamp());
        comment.setUser(userService.findOne(commentDTO.getUser().getId()));
        comment.setPost(postService.findOne(commentDTO.getPost().getId()));
        comment.setMainComment(commentService.findOne(commentDTO.getMainComment().getId()));

        comment = commentService.save(comment);
        model.addAttribute("comment", new CommentDTO(comment));
        return "commentSaved";
    }

    @PutMapping("/comments")
    public String updateComment(@RequestBody CommentDTO commentDTO, Model model) {
        Comment comment = commentService.findOne(commentDTO.getId());
        if (comment == null) {
            return "commentNotFound";
        }
        comment.setIsDeleted(commentDTO.getIsDeleted());
        comment.setText(commentDTO.getText());
        comment.setTimestamp(commentDTO.getTimestamp());
        comment.setUser(userService.findOne(commentDTO.getUser().getId()));
        comment.setPost(postService.findOne(commentDTO.getPost().getId()));
        comment.setMainComment(commentService.findOne(commentDTO.getMainComment().getId()));

        comment = commentService.save(comment);
        model.addAttribute("comment", new CommentDTO(comment));
        return "commentUpdated";
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

    @GetMapping("/comments/subcomments/{id}")
    public String getSubCommentsForComment(@PathVariable Long id, Model model) {
        Comment comment = commentService.findOne(id);
        if (comment == null || comment.getSubComments() == null) {
            return "subCommentsNotFound";
        }
        ArrayList<CommentDTO> subComments = new ArrayList<>();
        for (Comment subComment : comment.getSubComments()) {
            subComments.add(new CommentMapper().modelToDto(subComment));
        }
        model.addAttribute("subComments", subComments);
        return "viewSubComments";
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
