package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.FlairDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.model.Community;
import com.example.reddit.model.Flair;
import com.example.reddit.model.Post;
import com.example.reddit.model.User;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.FlairService;
import com.example.reddit.service.UserService;

@Controller
public class FlairController {

    @Autowired
    private FlairService flairService;

    @Autowired
    private CommunityService communityService;
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/flairs/add/{communityId}")
    public String addFlairToCommunity(@PathVariable Long communityId, @RequestParam String flairName, Model model, HttpSession session) {
        Community community = communityService.findOne(communityId);
        if (community == null) {
            model.addAttribute("error", "Community not found");
            return "error";
        }

        Flair flair = null;
        for (Flair f : flairService.findAll()) {
            if (f.getName().equalsIgnoreCase(flairName)) {
                flair = f;
                break;
            }
        }

        if (flair == null) {
            flair = new Flair();
            flair.setName(flairName);
            flairService.save(flair); // Save the new flair
        }

        Set<Community> communities = flair.getCommunities();
        communities.add(community);
        flair.setCommunities(communities);

        Set<Flair> flairs = community.getFlairs();
        flairs.add(flair);
        community.setFlairs(flairs);

        flairService.save(flair);
        communityService.save(community);

        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post post : community.getPosts()) {
            PostDTO postDTO = new PostDTO(post);
            postsDTO.add(postDTO);
        }

        CommunityDTO communityDTO = new CommunityDTO(community);

        List<FlairDTO> flairsDTO = new ArrayList<>();
        for (Flair f : community.getFlairs()) {
            FlairDTO flairDTO = new FlairDTO();
            flairDTO.setId(f.getId());
            flairDTO.setName(f.getName());
            flairsDTO.add(flairDTO);
        }
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            return "redirect:/index";
        }
        List<Community> communitiesAll = communityService.findAll();

        model.addAttribute("user", currentUser);
        model.addAttribute("communities", communitiesAll);

        //model.addAttribute("community", communityDTO);
        //model.addAttribute("posts", postsDTO);
        //model.addAttribute("flairs", flairsDTO);

        return "communities";
    }




    
    
    @GetMapping("/flairs/all")
    public String getAllFlairs(Model model) {
        List<Flair> flairs = flairService.findAll();
        List<FlairDTO> flairsDTO = new ArrayList<>();
        for (Flair flair : flairs) {
            FlairDTO flairDTO = new FlairDTO(flair);
            flairsDTO.add(flairDTO);
        }
        model.addAttribute("flairs", flairsDTO);
        return "listFlairs";
    }

    @GetMapping("/flairs/{id}")
    public String getFlair(@PathVariable Long id, Model model) {
        Flair flair = flairService.findOne(id);
        if (flair == null) {
            return "flairNotFound";
        }
        FlairDTO flairDTO = new FlairDTO(flair);
        model.addAttribute("flair", flairDTO);
        return "viewFlair";
    }

    @PostMapping("/flairs")
    public String saveFlair(@RequestBody FlairDTO flairDTO, Model model) {
        Flair flair = new Flair();
        flair.setName(flairDTO.getName());

        Set<Community> communities = new HashSet<>();
//        for (CommunityDTO communityDTO : flairDTO.getCommunities()) {
//            communities.add(convertToCommunity(communityDTO));
//        }
        flair.setCommunities(communities);

        flair = flairService.save(flair);
        model.addAttribute("flair", new FlairDTO(flair));
        return "flairSaved";
    }

    private Community convertToCommunity(CommunityDTO communityDTO) {
        Community community = new Community();
        community.setId(communityDTO.getId());
        // Set other properties...
        return community;
    }

    @PutMapping("/flairs")
    public String updateFlair(@RequestBody FlairDTO flairDTO, Model model) {
        Flair flair = flairService.findOne(flairDTO.getId());
        if (flair == null) {
            return "flairNotFound";
        }

        flair.setName(flairDTO.getName());

        List<Community> communities = new ArrayList<>();
//        for (CommunityDTO communityDTO : flairDTO.getCommunities()) {
//            communities.add(convertToCommunity(communityDTO));
//        }
        flair.setCommunities(new HashSet<>(communities));

        flair = flairService.save(flair);
        model.addAttribute("flair", new FlairDTO(flair));
        return "flairUpdated";
    }

    @DeleteMapping("/flairs/{id}")
    public String deleteFlair(@PathVariable Long id, Model model) {
        Flair flair = flairService.findOne(id);
        if (flair != null) {
            flairService.remove(id);
            return "flairDeleted";
        } else {
            return "flairNotFound";
        }
    }
}
