package com.example.reddit.mapper;

import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.model.Reaction;

import org.springframework.stereotype.Component;

@Component
public class ReactionMapper {

    public ReactionDTO modelToDto(Reaction reaction) {
        ReactionDTO reactionDTO = new ReactionDTO();

        reactionDTO.setId(reaction.getId());
        reactionDTO.setTimestamp(reaction.getTimestamp());
        reactionDTO.setType(reaction.getType());



        return reactionDTO;
    }

    public Reaction dtoToModel(ReactionDTO reactionDTO) {
        Reaction reaction = new Reaction();

        reaction.setId(reactionDTO.getId());
        reaction.setTimestamp(reactionDTO.getTimestamp());
        reaction.setType(reactionDTO.getType());



        return reaction;
    }
}
