package com.example.reddit.service.interfaces;


import com.example.reddit.indexModel.CommunityIndex;
import com.example.reddit.indexModel.PostIndex;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SearchService {

    Page<CommunityIndex> simpleSearchCommunity(List<String> keywords, Pageable pageable);

    Page<CommunityIndex> advancedSearchComunity(List<String> expression, Pageable pageable);
    
    Page<PostIndex> simpleSearchPost(List<String> keywords, Pageable pageable);

    Page<PostIndex> advancedSearchPost(List<String> expression, Pageable pageable);
}
