package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.mapper.BanMapper;
import com.example.reddit.mapper.PostMapper;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Community;
import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;

@RestController
@RequestMapping(value="api/communities")
public class CommunityController {

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
	
	@RequestMapping(value="/getCommunities", method = RequestMethod.GET)
	public ResponseEntity<List<CommunityDTO>> getAllCommunitys() {
		List<Community> communitys = communityService.findAll();
		
		List<CommunityDTO> communitysDTO = new ArrayList();
		for (Community obj : communitys) {
			CommunityDTO community = new CommunityDTO (obj);
			
			communitysDTO.add(community);
		}
		return new ResponseEntity(communitysDTO, HttpStatus.OK);
	}
	
    @GetMapping("/details/{id}")
    public String getCommunityDetails(@PathVariable Long id, Model model) {
        Community community = communityService.findOne(id);
        if (community == null) {
            return "communityNotFound"; // Handle case where community is not found
        }

        CommunityDTO communityDTO = new CommunityDTO(community);
        
        model.addAttribute("community", communityDTO);
        return "detailsCommunity"; // This should be the logical view name
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
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CommunityDTO> saveCommunity(@RequestBody CommunityDTO communityDTO){		
		Community community = new Community();
		
		community.setCreationDate(communityDTO.getCreationDate());
		community.setDescription(communityDTO.getDescription());
		community.setIsSuspended(communityDTO.getIsSuspended());
		community.setSuspendedReason(communityDTO.getSuspendedReason());
		community.setUser(userService.findOne(communityDTO.getUser().getId()));
//		community.setBans(BanDTOToModel(communityDTO.getBans()));
//		community.setPosts(PostDTOToModel(communityDTO.getPosts()));

		
		community = communityService.save(community);
		return new ResponseEntity(HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CommunityDTO> updateCommunity(@RequestBody CommunityDTO communityDTO){
		
		Community community = communityService.findOne(communityDTO.getId()); 
		if (community == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		community.setCreationDate(communityDTO.getCreationDate());
		community.setDescription(communityDTO.getDescription());
		community.setIsSuspended(communityDTO.getIsSuspended());
		community.setSuspendedReason(communityDTO.getSuspendedReason());
		community.setUser(userService.findOne(communityDTO.getUser().getId()));
//		community.setBans(BanDTOToModel(communityDTO.getBans()));
//		community.setPosts(PostDTOToModel(communityDTO.getPosts()));
		
		community = communityService.save(community);
		return new ResponseEntity(new CommunityDTO(community), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCommunity(@PathVariable Long id){
		Community community = communityService.findOne(id);

		if (community != null){
			
			communityService.remove(id);
			return new ResponseEntity(HttpStatus.OK);
		} else {		
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	 public ArrayList<BanDTO> getBansForCommunity(Long id) {
	        Community community = communityService.findOne(id); // Assuming you have a CommunityService to retrieve Community by ID

	        if (community == null || community.getBans() == null) {
	            return new ArrayList(); // or null, depending on your preference
	        }

	        ArrayList<BanDTO> bans = new ArrayList();
	        for (Ban ban : community.getBans()) {
	            bans.add(new BanMapper().modelToDto(ban));
	        }

	        return bans;
	    }
	 
	 public ArrayList<PostDTO> getPostsForCommunity(Long id) {
	        Community community = communityService.findOne(id); // Assuming you have a CommunityService to retrieve Community by ID

	        if (community == null || community.getPosts() == null) {
	            return new ArrayList(); // or null, depending on your preference
	        }

	        ArrayList<PostDTO> posts = new ArrayList();
	        for (Post post : community.getPosts()) {
	            posts.add(new PostMapper().modelToDto(post));
	        }

	        return posts;
	    }
	 
}
