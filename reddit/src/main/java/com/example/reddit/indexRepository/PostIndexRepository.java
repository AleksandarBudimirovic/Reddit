package com.example.reddit.indexRepository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Post;

public interface PostIndexRepository extends ElasticsearchRepository<Post, String>  {
//	List<Post> findByCommunityId(Long communityId);

}
