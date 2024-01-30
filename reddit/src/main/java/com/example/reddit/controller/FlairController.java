package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.FlairDTO;
import com.example.reddit.model.Community;
import com.example.reddit.model.Flair;
import com.example.reddit.service.CommunityService;
import com.example.reddit.service.FlairService;

@RestController
@RequestMapping(value = "api/flairs")
public class FlairController {

    @Autowired
    private FlairService flairService;
    
    @Autowired
    private CommunityService communityService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<FlairDTO>> getAllFlairs() {
        List<Flair> flairs = flairService.findAll();

        List<FlairDTO> flairsDTO = new ArrayList();
        for (Flair flair : flairs) {
            FlairDTO flairDTO = new FlairDTO(flair);
            flairsDTO.add(flairDTO);
        }
        return new ResponseEntity(flairsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlairDTO> getFlair(@PathVariable Long id) {
        Flair flair = flairService.findOne(id);
        if (flair == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        FlairDTO flairDTO = new FlairDTO(flair);

        return new ResponseEntity(flairDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<FlairDTO> saveFlair(@RequestBody FlairDTO flairDTO) {
        Flair flair = new Flair();
        flair.setName(flairDTO.getName());

        Set<Community> communities = new HashSet<>();
        
        for (CommunityDTO communityDTO : flairDTO.getCommunity()) {
            // Convert CommunityDTO to Community and add to the list
            communities.add(convertToCommunity(communityDTO));
        }

        flair.setCommunities(communities);

        flair = flairService.save(flair);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    private Community convertToCommunity(CommunityDTO communityDTO) {
        Community community = new Community();
        // Set properties from CommunityDTO to Community
        community.setId(communityDTO.getId());
        // Set other properties...

        return community;
    }

//    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
//    public ResponseEntity<FlairDTO> updateFlair(@RequestBody FlairDTO flairDTO) {
//        Flair flair = flairService.findOne(flairDTO.getId());
//        if (flair == null) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//        // Update properties from DTO to Entity
//        flair.setName(flairDTO.getName());
//
//        // Assuming you have a method to find all Communities by IDs in your FlairService
//        List<Community> communities = communityService.findAllByIds(flairDTO.getCommunity());
//        flair.setCommunity(communities);
//
//        flair = flairService.save(flair);
//        return new ResponseEntity(new FlairDTO(flair), HttpStatus.OK);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFlair(@PathVariable Long id) {
        Flair flair = flairService.findOne(id);

        if (flair != null) {
            flairService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
