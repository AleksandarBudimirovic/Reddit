package com.example.reddit.indexRepository;



import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.reddit.model.Community;

public interface CommunityIndexRepository extends ElasticsearchRepository<Community, String>  {

	
	
}
