package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.FlairDTO;
import com.example.reddit.model.Community;
import com.example.reddit.model.Flair;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.FlairService;

@Controller
public class FlairController {

    @Autowired
    private FlairService flairService;

    @Autowired
    private CommunityService communityService;

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
