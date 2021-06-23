package com.udc.muei.tfm.profiledataservice.model.topic;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * 
 * The Interface TopicRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface TopicRepository extends MongoRepository<Topic, String> {

	/**
	 * findByTopicName
	 * 
	 * @param topicName
	 * @return List<Topic>
	 */
	Optional<Topic> findByTopicName(String topicName);

}
