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
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Reaction;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;
import com.example.reddit.model.Post;

@RestController
@RequestMapping(value="api/reaction")
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
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<ReactionDTO>> getAllReactions() {
		List<Reaction> reactions = reactionService.findAll();
		
		List<ReactionDTO> reactionsDTO = new ArrayList<>();
		for (Reaction obj : reactions) {
			ReactionDTO reaction = new ReactionDTO (obj);
			
			reactionsDTO.add(reaction);
		}
		return new ResponseEntity<>(reactionsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ReactionDTO> getReaction(@PathVariable Long id){
		Reaction reaction = reactionService.findOne(id);
		if(reaction == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ReactionDTO reactionDTO = new ReactionDTO(reaction);
		
		
		return new ResponseEntity<>(reactionDTO, HttpStatus.OK);
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
	public ResponseEntity<ReactionDTO> saveReaction(@RequestBody ReactionDTO reactionDTO){		
		Reaction reaction = new Reaction();
		
		reaction.setTimestamp(reactionDTO.getTimestamp());
		reaction.setType(reactionDTO.getType());
		reaction.setComment(commentService.findOne(reactionDTO.getComment().getId()));
		reaction.setPost(postService.findOne(reactionDTO.getPost().getId()));
		reaction.setUser(userService.findOne(reactionDTO.getUser().getId()));

		
		reaction = reactionService.save(reaction);
		return new ResponseEntity<>(HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ReactionDTO> updateReaction(@RequestBody ReactionDTO reactionDTO){
		
		Reaction reaction = reactionService.findOne(reactionDTO.getId()); 
		if (reaction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		reaction.setTimestamp(reactionDTO.getTimestamp());
		reaction.setType(reactionDTO.getType());
		reaction.setComment(commentService.findOne(reactionDTO.getComment().getId()));
		reaction.setPost(postService.findOne(reactionDTO.getPost().getId()));
		reaction.setUser(userService.findOne(reactionDTO.getUser().getId()));
		
		reaction = reactionService.save(reaction);
		return new ResponseEntity<>(new ReactionDTO(reaction), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReaction(@PathVariable Long id){
		Reaction reaction = reactionService.findOne(id);

		if (reaction != null){
			
			reactionService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
