package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.model.Community;

@Controller
@RequestMapping(value="api/view")
public class ViewController {
    @RequestMapping(value="/listCommunities", method = RequestMethod.GET)
    public String getAllCommunitys(Model model) {
        
        return "listCommunities"; // Assuming "listCommunities" is the view name
        //return "redirect:/api/communities/listCommunities";
    }

}
