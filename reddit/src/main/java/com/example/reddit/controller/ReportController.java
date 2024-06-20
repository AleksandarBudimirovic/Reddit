package com.example.reddit.controller;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.PostMapper;
import com.example.reddit.mapper.ReportMapper;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Report;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private BanService banService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports/all")
    public String getAllReports(Model model) {
        List<Report> reports = reportService.findAll();
        List<ReportDTO> reportsDTO = new ArrayList<>();
        for (Report report : reports) {
            reportsDTO.add(new ReportDTO(report));
        }
        model.addAttribute("reports", reportsDTO);
        return "reports";
    }

    @GetMapping("/reports/{id}")
    public String getReport(@PathVariable Long id, Model model) {
        Report report = reportService.findOne(id);
        if (report == null) {
            return "error/404";
        }
        ReportDTO reportDTO = new ReportDTO(report);
        model.addAttribute("report", reportDTO);
        return "report";
    }

    @PostMapping("/reports")
    public String saveReport(@RequestBody ReportDTO reportDTO, Model model) {
        Report report = new Report();
        report.setAccepted(reportDTO.getAccepted());
        report.setReason(reportDTO.getReason());
        report.setTimestamp(reportDTO.getTimestamp());
        report.setComment(commentService.findOne(reportDTO.getComment().getId()));
        report.setPost(postService.findOne(reportDTO.getPost().getId()));
        report = reportService.save(report);
        return "redirect:/reports/all";
    }

    @PutMapping("/reports/{id}")
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

    @DeleteMapping("/reports/{id}")
    public String deleteReport(@PathVariable Long id) {
        Report report = reportService.findOne(id);
        if (report != null) {
            reportService.remove(id);
            return "redirect:/reports/all";
        } else {
            return "error/404";
        }
    }

    @ModelAttribute("comment")
    public CommentDTO getCommentForReport(Long id) {
        Report report = reportService.findOne(id);
        if (report == null || report.getComment() == null) {
            return null;
        }
        return new CommentMapper().modelToDto(report.getComment());
    }

    @ModelAttribute("post")
    public PostDTO getPostForReport(Long id) {
        Report report = reportService.findOne(id);
        if (report == null || report.getPost() == null) {
            return null;
        }
        return new PostMapper().modelToDto(report.getPost());
    }
}
