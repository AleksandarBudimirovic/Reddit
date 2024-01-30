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
import com.example.reddit.dto.FlairDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.mapper.CommentMapper;
import com.example.reddit.mapper.CommunityMapper;
import com.example.reddit.mapper.FlairMapper;
import com.example.reddit.mapper.ReactionMapper;
import com.example.reddit.mapper.ReportMapper;
import com.example.reddit.mapper.UserMapper;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Flair;
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
import com.example.reddit.model.Post;

@RestController
@RequestMapping(value="api/posts")
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
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> getAllPosts() {
		List<Post> posts = postService.findAll();
		
		List<PostDTO> postsDTO = new ArrayList();
		for (Post obj : posts) {
			PostDTO post = new PostDTO (obj);
			
			postsDTO.add(post);
		}
		return new ResponseEntity(postsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PostDTO> getPost(@PathVariable Long id){
		Post post = postService.findOne(id);
		if(post == null){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		PostDTO postDTO = new PostDTO(post);
		
		
		return new ResponseEntity(postDTO, HttpStatus.OK);
	}
	
	public ArrayList<Post> PostDTOToModel(List<PostDTO> listDTO) {
		ArrayList<Post> list=new ArrayList();
		for(PostDTO objectDTO : listDTO) {
			list.add(postService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Ban> BanDTOToModel(List<BanDTO> listDTO) {
		ArrayList<Ban> list=new ArrayList();
		for(BanDTO objectDTO : listDTO) {
			list.add(banService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Comment> CommentDTOToModel(List<CommentDTO> listDTO) {
		ArrayList<Comment> list=new ArrayList();
		for(CommentDTO objectDTO : listDTO) {
			list.add(commentService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Reaction> ReactionDTOToModel(List<ReactionDTO> listDTO) {
		ArrayList<Reaction> list=new ArrayList();
		for(ReactionDTO objectDTO : listDTO) {
			list.add(reactionService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	public ArrayList<Report> ReportDTOToModel(List<ReportDTO> listDTO) {
		ArrayList<Report> list=new ArrayList();
		for(ReportDTO objectDTO : listDTO) {
			list.add(reportService.findOne(objectDTO.getId()));
		}
		return list;
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){		
		Post post = new Post();
		
		post.setCommunity(communityService.findOne(postDTO.getCommunity().getId()));
		post.setCreationDate(postDTO.getCreationDate());
		post.setImagePath(postDTO.getImagePath());
		post.setText(postDTO.getText());
		post.setTitle(postDTO.getTitle());
		post.setUser(userService.findOne(postDTO.getUser().getId()));
		post.setComments(CommentDTOToModel(postDTO.getComments()));
		post.setReactions(ReactionDTOToModel(postDTO.getReactions()));
		post.setReports(ReportDTOToModel(postDTO.getReports()));
		
		
		post = postService.save(post);
		return new ResponseEntity(HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO){
		
		Post post = postService.findOne(postDTO.getId()); 
		if (post == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		post.setCommunity(communityService.findOne(postDTO.getCommunity().getId()));
		post.setCreationDate(postDTO.getCreationDate());
		post.setImagePath(postDTO.getImagePath());
		post.setText(postDTO.getText());
		post.setTitle(postDTO.getTitle());
		post.setUser(userService.findOne(postDTO.getUser().getId()));
		post.setComments(CommentDTOToModel(postDTO.getComments()));
		post.setReactions(ReactionDTOToModel(postDTO.getReactions()));
		post.setReports(ReportDTOToModel(postDTO.getReports()));
		
		post = postService.save(post);
		return new ResponseEntity(new PostDTO(post), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable Long id){
		Post post = postService.findOne(id);

		if (post != null){
			
			postService.remove(id);
			return new ResponseEntity(HttpStatus.OK);
		} else {		
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	public ArrayList<CommentDTO> getCommentsForPost(Long id) {
        Post post = postService.findOne(id); // Assuming you have a PostService to retrieve Post by ID

        if (post == null || post.getComments() == null) {
            return new ArrayList(); // or null, depending on your preference
        }

        ArrayList<CommentDTO> comments = new ArrayList();
        for (Comment comment : post.getComments()) {
            comments.add(new CommentMapper().modelToDto(comment));
        }

        return comments;
    }


    public CommunityDTO getCommunityForPost(Long id) {
        Post post = postService.findOne(id); // Assuming you have a PostService to retrieve Post by ID

        if (post == null || post.getCommunity() == null) {
            return null;
        }

        return new CommunityMapper().modelToDto(post.getCommunity());
    }

    public UserDTO getUserForPost(Long id) {
        Post post = postService.findOne(id); // Assuming you have a PostService to retrieve Post by ID

        if (post == null || post.getUser() == null) {
            return null;
        }

        return new UserMapper().modelToDto(post.getUser());
    }

    public ArrayList<ReactionDTO> getReactionsForPost(Long id) {
        Post post = postService.findOne(id); // Assuming you have a PostService to retrieve Post by ID

        if (post == null || post.getReactions() == null) {
            return new ArrayList(); // or null, depending on your preference
        }

        ArrayList<ReactionDTO> reactions = new ArrayList();
        for (Reaction reaction : post.getReactions()) {
            reactions.add(new ReactionMapper().modelToDto(reaction));
        }

        return reactions;
    }

    public ArrayList<ReportDTO> getReportsForPost(Long id) {
        Post post = postService.findOne(id); // Assuming you have a PostService to retrieve Post by ID

        if (post == null || post.getReports() == null) {
            return new ArrayList(); // or null, depending on your preference
        }

        ArrayList<ReportDTO> reports = new ArrayList();
        for (Report report : post.getReports()) {
            reports.add(new ReportMapper().modelToDto(report));
        }

        return reports;
    }
	
}
