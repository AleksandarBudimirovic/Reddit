package com.example.reddit.controller;

import java.util.ArrayList;
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

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.mapper.BanMapper;
import com.example.reddit.mapper.CommunityMapper;
import com.example.reddit.mapper.UserMapper;
import com.example.reddit.model.Ban;
import com.example.reddit.service.BanService;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.PostService;
import com.example.reddit.service.ReactionService;
import com.example.reddit.service.ReportService;
import com.example.reddit.service.UserService;


@RestController
public class BanController {

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
	
//	@RequestMapping(value="/all", method = RequestMethod.GET)
//	public ResponseEntity<List<BanDTO>> getAllBans() {
//		List<Ban> bans = banService.findAll();
//		
//		List<BanDTO> bansDTO = new ArrayList();
//		for (Ban obj : bans) {
//			BanDTO ban = new BanDTO (obj);
//			
//			bansDTO.add(ban);
//		}
//		return new ResponseEntity(bansDTO, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/{id}", method=RequestMethod.GET)
//	public ResponseEntity<BanDTO> getBan(@PathVariable Long id){
//		Ban ban = banService.findOne(id);
//		if(ban == null){
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//		
//		BanDTO banDTO = new BanDTO(ban);
//		
//		
//		return new ResponseEntity(banDTO, HttpStatus.OK);
//	}
//	
//	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
//	public ResponseEntity<BanDTO> saveBan(@RequestBody BanDTO banDTO){		
//		Ban ban = new Ban();
//		
//		ban.setUser(userService.findOne(banDTO.getUser().getId()));
//		ban.setTimestamp(banDTO.getTimestamp());
//		ban.setRCommunity(communityService.findOne(banDTO.getCommunity().getId()));
//		
//		ban = banService.save(ban);
//		return new ResponseEntity(HttpStatus.CREATED);	
//	}
//	
//	
//	
//	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
//	public ResponseEntity<BanDTO> updateBan(@RequestBody BanDTO banDTO){
//		
//		Ban ban = banService.findOne(banDTO.getId()); 
//		if (ban == null) {
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
//		
//		ban.setUser(userService.findOne(banDTO.getUser().getId()));
//		ban.setTimestamp(banDTO.getTimestamp());
//		ban.setRCommunity(communityService.findOne(banDTO.getCommunity().getId()));
//		
//		ban = banService.save(ban);
//		return new ResponseEntity(new BanDTO(ban), HttpStatus.OK);	
//	}
//	
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	public ResponseEntity<Void> deleteBan(@PathVariable Long id){
//		Ban ban = banService.findOne(id);
//
//		if (ban != null){
//			
//			banService.remove(id);
//			return new ResponseEntity(HttpStatus.OK);
//		} else {		
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	public CommunityDTO getCommunityForBan(Long id) {
//	    Ban ban = banService.findOne(id); // Assuming you have a BanService to retrieve Ban by ID
//
//	    if (ban == null || ban.getCommunity() == null) {
//	        return null;
//	    }
//
//	    return new CommunityMapper().modelToDto(ban.getCommunity());
//	}
//
//	public UserDTO getUserForBan(Long id) {
//	    Ban ban = banService.findOne(id); // Assuming you have a BanService to retrieve Ban by ID
//
//	    if (ban == null || ban.getUser() == null) {
//	        return null;
//	    }
//
//	    return new UserMapper().modelToDto(ban.getUser());
//	}


	
}
