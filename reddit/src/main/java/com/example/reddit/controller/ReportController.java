package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.PostMapper;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Report;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;
import com.example.reddit.model.Post;

@RestController
@RequestMapping(value="api/reports")
public class ReportController {

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
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<ReportDTO>> getAllReports() {
		List<Report> reports = reportService.findAll();
		
		List<ReportDTO> reportsDTO = new ArrayList<>();
		for (Report obj : reports) {
			ReportDTO report = new ReportDTO (obj);
			
			reportsDTO.add(report);
		}
		return new ResponseEntity<>(reportsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ReportDTO> getReport(@PathVariable Long id){
		Report report = reportService.findOne(id);
		if(report == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ReportDTO reportDTO = new ReportDTO(report);
		
		
		return new ResponseEntity<>(reportDTO, HttpStatus.OK);
	}
	
	public ArrayList<Post> PostDTOToModel(List<PostDTO> listDTO) {
		ArrayList<Post> list=new ArrayList<>();
		for(PostDTO objectDTO : listDTO) {
			list.add(postService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Ban> BanDTOToModel(List<BanDTO> listDTO) {
		ArrayList<Ban> list=new ArrayList<>();
		for(BanDTO objectDTO : listDTO) {
			list.add(banService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ReportDTO> saveReport(@RequestBody ReportDTO reportDTO){		
		Report report = new Report();
		
		report.setAccepted(reportDTO.getAccepted());
		report.setReason(reportDTO.getReason());
		report.setTimestamp(reportDTO.getTimestamp());
		report.setComment(commentService.findOne(reportDTO.getComment().getId()));
		report.setPost(postService.findOne(reportDTO.getPost().getId()));
		
		report = reportService.save(report);
		return new ResponseEntity<>(HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ReportDTO> updateReport(@RequestBody ReportDTO reportDTO){
		
		Report report = reportService.findOne(reportDTO.getId()); 
		if (report == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		report.setAccepted(reportDTO.getAccepted());
		report.setReason(reportDTO.getReason());
		report.setTimestamp(reportDTO.getTimestamp());
		report.setComment(commentService.findOne(reportDTO.getComment().getId()));
		report.setPost(postService.findOne(reportDTO.getPost().getId()));
		
		report = reportService.save(report);
		return new ResponseEntity<>(new ReportDTO(report), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReport(@PathVariable Long id){
		Report report = reportService.findOne(id);

		if (report != null){
			
			reportService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	 public CommentDTO getCommentForReport(Long id) {
	        Report report = reportService.findOne(id); // Assuming you have a ReportService to retrieve Report by ID

	        if (report == null || report.getComment() == null) {
	            return null;
	        }

	        return new CommentMapper().modelToDto(report.getComment());
	    }

	    public PostDTO getPostForReport(Long id) {
	        Report report = reportService.findOne(id); // Assuming you have a ReportService to retrieve Report by ID

	        if (report == null || report.getPost() == null) {
	            return null;
	        }

	        return new PostMapper().modelToDto(report.getPost());
	    }
	    
}
