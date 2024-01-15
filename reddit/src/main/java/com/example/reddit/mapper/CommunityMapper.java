package com.example.reddit.mapper;

import com.example.reddit.dto.CommunityDTO;
import com.example.reddit.model.Community;

import org.springframework.stereotype.Component;

@Component
public class CommunityMapper {

    public CommunityDTO modelToDto(Community community) {
        CommunityDTO communityDTO = new CommunityDTO();

        communityDTO.setId(community.getId());
        communityDTO.setCreationDate(community.getCreationDate());
        communityDTO.setDescription(community.getDescription());
        communityDTO.setIsSuspended(community.getIsSuspended());
        communityDTO.setSuspendedReason(community.getSuspendedReason());



        return communityDTO;
    }

    public Community dtoToModel(CommunityDTO communityDTO) {
        Community community = new Community();

        community.setId(communityDTO.getId());
        community.setCreationDate(communityDTO.getCreationDate());
        community.setDescription(communityDTO.getDescription());
        community.setIsSuspended(communityDTO.getIsSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());



        return community;
    }
}
