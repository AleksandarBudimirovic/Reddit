package com.example.reddit.controller;

import com.example.reddit.dto.*;
import com.example.reddit.mapper.*;
import com.example.reddit.model.*;
import com.example.reddit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
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

    @GetMapping("/communities")
    public String getAllCommunities(Model model) {
        List<Community> communities = communityService.findAll();

        List<CommunityDTO> communitiesDTO = new ArrayList<>();
        for (Community community : communities) {
            communitiesDTO.add(new CommunityDTO(community));
        }

        // Logging each community description to console
        for (CommunityDTO communityDTO : communitiesDTO) {
            System.out.println("Community Description: " + communityDTO.getDescription());
        }

        model.addAttribute("communities", communitiesDTO);
        return "listCommunities";
    }

    @GetMapping("/comunities/details/{id}")
    public String getCommunityDetails(@PathVariable Long id, Model model) {
        Community community = communityService.findOne(id);
        if (community == null) {
            // Handle not found scenario
            return "error";
        }

        model.addAttribute("community", new CommunityDTO(community));
        return "communityDetails";
    }

//    @PostMapping(consumes="application/json")
//    public ResponseEntity<CommunityDTO> saveCommunity(@RequestBody CommunityDTO communityDTO) {
//        Community community = new Community();
//
//        community.setCreationDate(communityDTO.getCreationDate());
//        community.setDescription(communityDTO.getDescription());
//        community.setIsSuspended(communityDTO.getIsSuspended());
//        community.setSuspendedReason(communityDTO.getSuspendedReason());
//        community.setUser(userService.findOne(communityDTO.getUser().getId()));
//        // Set other properties as needed
//
//        community = communityService.save(community);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping(consumes="application/json")
//    public ResponseEntity<CommunityDTO> updateCommunity(@RequestBody CommunityDTO communityDTO) {
//        Community community = communityService.findOne(communityDTO.getId());
//        if (community == null) {
//            // Handle not found scenario
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        community.setCreationDate(communityDTO.getCreationDate());
//        community.setDescription(communityDTO.getDescription());
//        community.setIsSuspended(communityDTO.getIsSuspended());
//        community.setSuspendedReason(communityDTO.getSuspendedReason());
//        community.setUser(userService.findOne(communityDTO.getUser().getId()));
//        // Set other properties as needed
//
//        community = communityService.save(community);
//        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
//        Community community = communityService.findOne(id);
//        if (community == null) {
//            // Handle not found scenario
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        communityService.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    // Methods to retrieve related entities
//
//    @GetMapping("/{id}/bans")
//    public String getBansForCommunity(@PathVariable Long id, Model model) {
//        List<BanDTO> bansDTO = getBansDTOs(id); // Assume a method to retrieve BanDTOs for community
//        model.addAttribute("bans", bansDTO);
//        return "bans";
//    }
//
//    @GetMapping("/{id}/posts")
//    public String getPostsForCommunity(@PathVariable Long id, Model model) {
//        List<PostDTO> postsDTO = getPostsDTOs(id); // Assume a method to retrieve PostDTOs for community
//        model.addAttribute("posts", postsDTO);
//        return "posts";
//    }
//
//    // Helper methods to convert DTOs to models
//    // These methods are similar to the ones you previously had for converting DTOs to entity models
//
//    private List<BanDTO> getBansDTOs(Long id) {
//        // Implement logic to retrieve BanDTOs for community with given id
//        return new ArrayList<>(); // Placeholder implementation
//    }
//
//    private List<PostDTO> getPostsDTOs(Long id) {
//        // Implement logic to retrieve PostDTOs for community with given id
//        return new ArrayList<>(); // Placeholder implementation
//    }
}

