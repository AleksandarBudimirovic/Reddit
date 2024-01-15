package com.example.reddit.mapper;

import org.springframework.stereotype.Component;

import com.example.reddit.dto.BanDTO;
import com.example.reddit.model.Ban;

@Component
public class BanMapper {

    public BanDTO modelToDto(Ban ban) {
        BanDTO banDTO = new BanDTO();

        banDTO.setId(ban.getId());
        banDTO.setTimestamp(ban.getTimestamp());

        return banDTO;
    }

    public Ban dtoToModel(BanDTO banDTO) {
        Ban ban = new Ban();

        ban.setId(banDTO.getId());
        ban.setTimestamp(banDTO.getTimestamp());


        return ban;
    }
}
