package com.example.reddit.controller;

import com.example.reddit.model.Comment;
import com.example.reddit.model.Post;
import com.example.reddit.model.Report;
import com.example.reddit.model.User;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BanController {

    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;
    private final ReportService reportService;

    @Autowired
    public BanController(PostService postService, CommentService commentService, UserService userService, ReportService reportService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
        this.reportService = reportService;
    }

    @PostMapping("/ban")
    public String banEntity(@RequestParam("entityType") String entityType, @RequestParam("entityId") Long entityId, RedirectAttributes redirectAttributes) {
        String resultMessage = "";
        switch (entityType) {
            case "post":
                resultMessage = banPost(entityId);
                break;
            case "comment":
                resultMessage = banComment(entityId);
                break;
            case "user":
                resultMessage = banUser(entityId);
                break;
            default:
                resultMessage = "Unsupported entity type";
                break;
        }
        redirectAttributes.addFlashAttribute("message", resultMessage);
        return "redirect:/reports/all";
    }

    private String banPost(Long postId) {
        Post post = postService.findOne(postId);
        if (post != null) {
            postService.remove(postId);
            Report report = reportService.findOne(postId);
            if (report != null) {
                report.setAccepted((byte) 1);  // Set accepted to 1
                reportService.save(report);
            }
            return "Post with ID " + postId + " has been deleted.";
        }
        return "Post not found.";
    }

    private String banComment(Long commentId) {
        Comment comment = commentService.findOne(commentId);
        if (comment != null) {
            commentService.remove(commentId);
            Report report = reportService.findOne(commentId);
            if (report != null) {
                report.setAccepted((byte) 1);  // Set accepted to 1
                reportService.save(report);
            }
            return "Comment with ID " + commentId + " has been deleted.";
        }
        return "Comment not found.";
    }

    private String banUser(Long userId) {
        User user = userService.findOne(userId);
        if (user != null) {
            user.setBanned(true);
            userService.save(user);
            Report report = reportService.findOne(userId);
            if (report != null) {
                report.setAccepted((byte) 1);  // Set accepted to 1
                reportService.save(report);
            }
            return "User with ID " + userId + " has been banned.";
        }
        return "User not found.";
    }

    @PostMapping("/reports/pardon/{id}")
    public String pardonReport(@PathVariable("id") Long reportId, RedirectAttributes redirectAttributes) {
        Report report = reportService.findOne(reportId);
        if (report != null) {
            report.setAccepted((byte) 0);  // Set accepted to 0
            reportService.save(report);
            redirectAttributes.addFlashAttribute("message", "Report with ID " + reportId + " has been pardoned.");
            return "redirect:/reports/all";
        }
        redirectAttributes.addFlashAttribute("error", "Report not found.");
        return "redirect:/reports/all";
    }
}
