package com.example.reddit.controller;

import com.example.reddit.dto.*;
import com.example.reddit.model.*;
import com.example.reddit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        model.addAttribute("communities", communitiesDTO);
        return "listCommunities";
    }

    @GetMapping("/communities/details/{id}")
    public String getCommunityDetails(@PathVariable Long id, Model model) {
        Community community = communityService.findOne(id);
        if (community == null) {
            return "error";
        }
        
        CommunityDTO communityDTO = new CommunityDTO(community);
        List<PostDTO> postsDTO = findByCommunityId(communityDTO.getId());

        // Print community ID to console
        System.out.println("Community ID: " + communityDTO.getId());

        // Print each post ID to console
        for (PostDTO postDTO : postsDTO) {
            System.out.println("Post ID: " + postDTO.getId());
        }

        model.addAttribute("community", communityDTO);
        model.addAttribute("posts", postsDTO);

        return "detailsCommunity";
    }

    public List<PostDTO> findByCommunityId(Long communityId) {
        List<Post> posts = postService.findByCommunityId(communityId);
        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post post : posts) {
            postsDTO.add(new PostDTO(post));
        }
        return postsDTO;
    }

    @GetMapping("/addCommunityForm")
    public String addCommunityForm(@RequestParam("communityId") Long communityId, Model model) {
        model.addAttribute("communityId", communityId);
        return "addCommunity"; 
    }
    
    @GetMapping("/editCommunityForm/{communityId}")
    public String editCommunityForm(@PathVariable("communityId") Long communityId, Model model) {
        Community community = communityService.findOne(communityId);
        model.addAttribute("community", community);
        return "editCommunity"; 
    }
    
    @PostMapping(value = "/communities", consumes = "application/json")
    public String saveCommunity(@RequestBody CommunityDTO communityDTO, Model model) {
        Community community = new Community();

        community.setCreationDate(communityDTO.getCreationDate());
        community.setDescription(communityDTO.getDescription());
        community.setIsSuspended(communityDTO.getIsSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());
        community.setUser(userService.findOne(communityDTO.getUser().getId()));
        // Set other properties as needed

        community = communityService.save(community);
        model.addAttribute("community", new CommunityDTO(community));
        return "redirect:/communities";
    }

    @PutMapping(value = "/communities", consumes = "application/json")
    public String updateCommunity(@RequestBody CommunityDTO communityDTO, Model model) {
        Community community = communityService.findOne(communityDTO.getId());
        if (community == null) {
            // Handle not found scenario
            return "error";
        }

        community.setCreationDate(communityDTO.getCreationDate());
        community.setDescription(communityDTO.getDescription());
        community.setIsSuspended(communityDTO.getIsSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());
        community.setUser(userService.findOne(communityDTO.getUser().getId()));
        // Set other properties as needed

        community = communityService.save(community);
        model.addAttribute("community", new CommunityDTO(community));
        return "redirect:/communities/details/" + community.getId();
    }

    @DeleteMapping("/communities/{id}")
    public String deleteCommunity(@PathVariable Long id, Model model) {
        Community community = communityService.findOne(id);
        if (community == null) {
            // Handle not found scenario
            return "error";
        }

        communityService.remove(id);
        return "redirect:/communities";
    }

    // Methods to retrieve related entities

    @GetMapping("/communities/{id}/bans")
    public String getBansForCommunity(@PathVariable Long id, Model model) {
        List<BanDTO> bansDTO = getBansDTOs(id); // Assume a method to retrieve BanDTOs for community
        model.addAttribute("bans", bansDTO);
        return "bans";
    }


    private List<BanDTO> getBansDTOs(Long id) {
        // Implement logic to retrieve BanDTOs for community with given id
        return new ArrayList<>(); // Placeholder implementation
    }


}
