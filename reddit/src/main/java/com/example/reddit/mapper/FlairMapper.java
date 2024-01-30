package com.example.reddit.mapper;

import com.example.reddit.dto.FlairDTO;
import com.example.reddit.model.Flair;

import org.springframework.stereotype.Component;

@Component
public class FlairMapper {

//    public FlairDTO modelToDto(Flair flair) {
//        FlairDTO flairDTO = new FlairDTO();
//
//        flairDTO.setId(flair.getId());
//        flairDTO.setName(flair.getName());
//
//
//        return flairDTO;
//    }

    public Flair dtoToModel(FlairDTO flairDTO) {
        Flair flair = new Flair();

        flair.setId(flairDTO.getId());
        flair.setName(flairDTO.getName());



        return flair;
    }
}
