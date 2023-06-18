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
import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Community;
import com.example.reddit.model.User;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;
import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;

@RestController
@RequestMapping(value="api/user")
public class UserController {

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
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.findAll();
		
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User obj : users) {
			UserDTO user = new UserDTO (obj);
			
			usersDTO.add(user);
		}
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		User user = userService.findOne(id);
		if(user == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		UserDTO userDTO = new UserDTO(user);
		
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
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
	
	public ArrayList<Comment> CommentDTOToModel(List<CommentDTO> listDTO) {
		ArrayList<Comment> list=new ArrayList<>();
		for(CommentDTO objectDTO : listDTO) {
			list.add(commentService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Community> CommunityDTOToModel(List<CommunityDTO> listDTO) {
		ArrayList<Community> list=new ArrayList<>();
		for(CommunityDTO objectDTO : listDTO) {
			list.add(communityService.findOne(objectDTO.getId()));
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
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){		
		User user = new User();
		
		user.setAvatar(userDTO.getAvatar());
		user.setDescription(userDTO.getDescription());
		user.setUsername(userDTO.getUsername());
		user.setRole(userDTO.getRole());
		user.setRegistrationDate(userDTO.getRegistrationDate());
		user.setPassword(userDTO.getPassword());
		user.setDisplayName(userDTO.getDisplayName());
		
		user.setBans(BanDTOToModel(userDTO.getBanned()));
		user.setComments(CommentDTOToModel(userDTO.getComments()));
		user.setCommunities(CommunityDTOToModel(userDTO.getCommunities()));
		user.setPosts(PostDTOToModel(userDTO.getPosts()));
		user.setReactions(ReactionDTOToModel(userDTO.getReactions()));
		
		
		user = userService.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
		
		User user = userService.findOne(userDTO.getId()); 
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		user.setAvatar(userDTO.getAvatar());
		user.setDescription(userDTO.getDescription());
		user.setUsername(userDTO.getUsername());
		user.setRole(userDTO.getRole());
		user.setRegistrationDate(userDTO.getRegistrationDate());
		user.setPassword(userDTO.getPassword());
		user.setDisplayName(userDTO.getDisplayName());
		
		user = userService.save(user);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		User user = userService.findOne(id);

		if (user != null){
			
			userService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
