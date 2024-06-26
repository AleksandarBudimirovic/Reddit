package com.example.reddit.controller;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.PostMapper;
import com.example.reddit.mapper.ReportMapper;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Post;
import com.example.reddit.model.Report;
import com.example.reddit.model.User;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class ReportController {

    @Autowired
    private BanService banService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private PostService postService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports/all")
    public String getAllReports(Model model, HttpSession session) {
    	User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            // Handle case where user is not logged in
            return "redirect:/";
        }
        List<Report> reports = reportService.findAll();
        List<ReportDTO> reportsDTO = new ArrayList<>();
        for (Report report : reports) {
            reportsDTO.add(new ReportDTO(report));
        }
        model.addAttribute("user", currentUser);
        model.addAttribute("reports", reportsDTO);
        return "reports";
    }

    @GetMapping("/reports/get/{id}")
    public String getReport(@PathVariable Long id, Model model) {
        Report report = reportService.findOne(id);
        if (report == null) {
            return "error/404";
        }
        ReportDTO reportDTO = new ReportDTO(report);
        model.addAttribute("report", reportDTO);
        return "report";
    }

    @PostMapping("/reports/add")
    public String saveReport(@RequestParam("reason") String reason,
                             @RequestParam(value = "commentId", required = false) Long commentId,
                             @RequestParam(value = "postId", required = false) Long postId,
                             @RequestParam(value = "reportedUserId", required = false) Long reportedUserId,
                             Model model, HttpSession session) {

        // Log incoming data
        System.out.println("Received reason: " + reason);
        System.out.println("Received reportedUserId: " + reportedUserId);
        Report report = new Report();
        report.setAccepted(null);
        report.setReason(reason);
        report.setTimestamp(new Date()); // Set current timestamp

        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/"; // Redirect to login page or home page as per your application flow
        }

        // Set comment if provided
        if (commentId != null) {
            Comment comment = commentService.findOne(commentId);
            if (comment != null) {
                report.setComment(comment);
            }
        }

        // Set post if provided
        if (postId != null) {
            Post post = postService.findOne(postId);
            if (post != null) {
                report.setPost(post);
            }
        }

        // Set reported user if provided
        if (reportedUserId != null) {
            User reportedUser = userService.findOne(reportedUserId);
            if (reportedUser != null) {
                report.setReportedUser(reportedUser);
            }
        }

        // Save the report
        report = reportService.save(report);

        model.addAttribute("report", new ReportDTO(report));
        return "redirect:/communities";
    }

    @PutMapping("/reports/update/{id}")
    public String updateReport(@PathVariable Long id, @RequestBody ReportDTO reportDTO, Model model) {
        Report report = reportService.findOne(id);
        if (report == null) {
            return "error/400";
        }
        report.setAccepted(reportDTO.getAccepted());
        report.setReason(reportDTO.getReason());
        report.setTimestamp(reportDTO.getTimestamp());
        report.setComment(commentService.findOne(reportDTO.getComment().getId()));
        report.setPost(postService.findOne(reportDTO.getPost().getId()));
        report = reportService.save(report);
        model.addAttribute("report", new ReportDTO(report));
        return "report";
    }

    @DeleteMapping("/reports/delete/{id}")
    public String deleteReport(@PathVariable Long id) {
        Report report = reportService.findOne(id);
        if (report != null) {
            reportService.remove(id);
            return "redirect:/reports/all";
        } else {
            return "error/404";
        }
    }

//    @ModelAttribute("comment")
//    public CommentDTO getCommentForReport(Long id) {
//        Report report = reportService.findOne(id);
//        if (report == null || report.getComment() == null) {
//            return null;
//        }
//        return new CommentMapper().modelToDto(report.getComment());
//    }
//
//    @ModelAttribute("post")
//    public PostDTO getPostForReport(Long id) {
//        Report report = reportService.findOne(id);
//        if (report == null || report.getPost() == null) {
//            return null;
//        }
//        return new PostMapper().modelToDto(report.getPost());
//    }
}
