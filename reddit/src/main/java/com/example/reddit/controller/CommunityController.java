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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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
    @Autowired
    private FlairService flairService;

    @GetMapping("/communities")
    public String getAllCommunities(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            // Handle case where user is not logged in
            return "redirect:/index";
        }

        // Retrieve all communities from the service layer
        List<Community> communities = communityService.findAll();

        // Filter out suspended communities
        List<CommunityDTO> communitiesDTO = new ArrayList<>();
        for (Community community : communities) {
            if (community.getIsSuspended() == 0) { // Assuming isSuspended is 0 for not suspended communities
                communitiesDTO.add(new CommunityDTO(community));
            }
        }

        // Add attributes to the model
        model.addAttribute("user", currentUser);
        model.addAttribute("communities", communitiesDTO);
        
        return "communities";
    }


    @GetMapping("/communities/details/{id}")
    public String getCommunityDetails(@PathVariable Long id, Model model) {
        Community community = communityService.findOne(id);
        if (community == null) {
            return "error";
        }
        
        CommunityDTO communityDTO = new CommunityDTO(community);
        List<PostDTO> postsDTO = findByCommunityId(communityDTO.getId());

        List<FlairDTO> flairsDTO = new ArrayList<>();
        for (Flair flair : community.getFlairs()) {
            FlairDTO flairDTO = new FlairDTO();
            flairDTO.setId(flair.getId());
            flairDTO.setName(flair.getName());
            flairsDTO.add(flairDTO);
        }

        model.addAttribute("community", communityDTO);
        model.addAttribute("posts", postsDTO);
        model.addAttribute("flairs", flairsDTO);

        return "community";
    }

    public List<PostDTO> findByCommunityId(Long communityId) {
        List<Post> posts = postService.findByCommunityId(communityId);
        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post post : posts) {
            postsDTO.add(new PostDTO(post));
        }
        return postsDTO;
    }
    
    @PostMapping("/communities/add")
    public String addCommunity(@RequestParam("communityName") String communityName,
                               @RequestParam("description") String description,
                               Model model, HttpSession session) {
        
        Community community = new Community();
        community.setName(communityName);
        community.setDescription(description);
        community.setCreationDate(new Date()); 
        community.setIsSuspended((byte) 0);
        
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            return "redirect:/index";
        }
        community.setUser(currentUser); 
        community = communityService.save(community);
        model.addAttribute("community", new CommunityDTO(community));
        return "redirect:/communities";
    }



    
    @PostMapping("/communities/edit")
    public String editCommunity(@RequestParam Long id,
                                @RequestParam String description,
                                @RequestParam String name,
                                Model model) {
        Community existingCommunity = communityService.findOne(id);

        if (existingCommunity == null) {
            return "redirect:/error";
        }

        existingCommunity.setDescription(description);
        existingCommunity.setName(name);

        Community updatedCommunity = communityService.save(existingCommunity);

        return "redirect:/communities/details/" + updatedCommunity.getId();
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

    @PostMapping("/community/delete/{id}")
    public String deleteCommunity(@PathVariable Long id, Model model) {
        Community community = communityService.findOne(id);
        if (community == null) {
            // Handle not found scenario
            return "error";
        }

        // Set isSuspended to true instead of removing the community
        community.setIsSuspended((byte) 1);
        communityService.save(community); // Assuming save method updates the entity

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
