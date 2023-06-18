package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;

@RestController
@RequestMapping(value="api/comment")
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
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> getAllComments() {
		List<Comment> comments = commentService.findAll();
		
		List<CommentDTO> commentsDTO = new ArrayList<>();
		for (Comment obj : comments) {
			CommentDTO comment = new CommentDTO (obj);
			
			commentsDTO.add(comment);
		}
		return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CommentDTO> getComment(@PathVariable Long id){
		Comment comment = commentService.findOne(id);
		if(comment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CommentDTO commentDTO = new CommentDTO(comment);
		
		
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}
	
	public ArrayList<Comment> CommentDTOToModel(List<CommentDTO> listDTO) {
		ArrayList<Comment> list=new ArrayList<>();
		for(CommentDTO objectDTO : listDTO) {
			list.add(commentService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Reaction> ReactionDTOToModel(List<ReactionDTO> listDTO) {
		ArrayList<Reaction> list=new ArrayList<>();
		for(ReactionDTO objectDTO : listDTO) {
			list.add(reactionService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Report> ReportDTOToModel(List<ReportDTO> listDTO) {
		ArrayList<Report> list=new ArrayList<>();
		for(ReportDTO objectDTO : listDTO) {
			list.add(reportService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO){		
		Comment comment = new Comment();
		
		comment.setIsDeleted(commentDTO.getIsDeleted());
		comment.setText(commentDTO.getText());
		comment.setTimestamp(commentDTO.getTimestamp());
		comment.setUser(userService.findOne(commentDTO.getUser().getId()));
		comment.setPost(postService.findOne(commentDTO.getPost().getId()));
		comment.setMainComment(commentService.findOne(commentDTO.getMainComment().getId()));
		
		comment.setSubComments(CommentDTOToModel(commentDTO.getSubComments()));
		comment.setReactions(ReactionDTOToModel(commentDTO.getReactions()));
		comment.setReports(ReportDTOToModel(commentDTO.getReports()));
		
		comment = commentService.save(comment);
		return new ResponseEntity<>(HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO){
		
		Comment comment = commentService.findOne(commentDTO.getId()); 
		if (comment == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		comment.setIsDeleted(commentDTO.getIsDeleted());
		comment.setText(commentDTO.getText());
		comment.setTimestamp(commentDTO.getTimestamp());
		comment.setUser(userService.findOne(commentDTO.getUser().getId()));
		comment.setPost(postService.findOne(commentDTO.getPost().getId()));
		comment.setMainComment(commentService.findOne(commentDTO.getMainComment().getId()));
		
		comment.setSubComments(CommentDTOToModel(commentDTO.getSubComments()));
		comment.setReactions(ReactionDTOToModel(commentDTO.getReactions()));
		comment.setReports(ReportDTOToModel(commentDTO.getReports()));
		
		comment = commentService.save(comment);
		return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteComment(@PathVariable Long id){
		Comment comment = commentService.findOne(id);

		if (comment != null){
			
			commentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
