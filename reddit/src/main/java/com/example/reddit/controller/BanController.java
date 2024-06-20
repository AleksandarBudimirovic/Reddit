package com.example.reddit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.dto.UserDTO;
import com.example.reddit.mapper.CommunityMapper;
import com.example.reddit.mapper.UserMapper;
import com.example.reddit.model.Ban;
import com.example.reddit.service.*;

@Controller
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

    @GetMapping("/bans/all")
    public String getAllBans(Model model) {
        List<Ban> bans = banService.findAll();
        List<BanDTO> bansDTO = new ArrayList<>();
        for (Ban obj : bans) {
            BanDTO ban = new BanDTO(obj);
            bansDTO.add(ban);
        }
        model.addAttribute("bans", bansDTO);
        return "listBans";
    }

    @GetMapping("/bans/{id}")
    public String getBan(@PathVariable Long id, Model model) {
        Ban ban = banService.findOne(id);
        if (ban == null) {
            return "banNotFound";
        }
        BanDTO banDTO = new BanDTO(ban);
        model.addAttribute("ban", banDTO);
        return "viewBan";
    }

    @PostMapping("/bans")
    public String saveBan(@RequestBody BanDTO banDTO, Model model) {
        Ban ban = new Ban();
        ban.setUser(userService.findOne(banDTO.getUser().getId()));
        ban.setTimestamp(banDTO.getTimestamp());
        ban.setRCommunity(communityService.findOne(banDTO.getCommunity().getId()));
        ban = banService.save(ban);
        model.addAttribute("ban", new BanDTO(ban));
        return "banSaved";
    }

    @PutMapping("/bans")
    public String updateBan(@RequestBody BanDTO banDTO, Model model) {
        Ban ban = banService.findOne(banDTO.getId());
        if (ban == null) {
            return "banNotFound";
        }
        ban.setUser(userService.findOne(banDTO.getUser().getId()));
        ban.setTimestamp(banDTO.getTimestamp());
        ban.setRCommunity(communityService.findOne(banDTO.getCommunity().getId()));
        ban = banService.save(ban);
        model.addAttribute("ban", new BanDTO(ban));
        return "banUpdated";
    }

    @DeleteMapping("/bans/{id}")
    public String deleteBan(@PathVariable Long id, Model model) {
        Ban ban = banService.findOne(id);
        if (ban != null) {
            banService.remove(id);
            return "banDeleted";
        } else {
            return "banNotFound";
        }
    }

    @GetMapping("/bans/community/{id}")
    public String getCommunityForBan(@PathVariable Long id, Model model) {
        Ban ban = banService.findOne(id);
        if (ban == null || ban.getCommunity() == null) {
            return "communityNotFound";
        }
        model.addAttribute("community", new CommunityMapper().modelToDto(ban.getCommunity()));
        return "viewCommunity";
    }

    @GetMapping("/bans/user/{id}")
    public String getUserForBan(@PathVariable Long id, Model model) {
        Ban ban = banService.findOne(id);
        if (ban == null || ban.getUser() == null) {
            return "userNotFound";
        }
        model.addAttribute("user", new UserMapper().modelToDto(ban.getUser()));
        return "viewUser";
    }
}
