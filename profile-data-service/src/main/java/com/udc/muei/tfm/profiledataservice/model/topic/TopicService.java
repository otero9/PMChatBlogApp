package com.udc.muei.tfm.profiledataservice.model.topic;

import java.util.List;

/*
 * 
 * The Interface TopicService.
 * 
 * @author a.oteroc
 * 
 */
public interface TopicService {

	/**
	 * save
	 * 
	 * @param topic
	 * @return Topic
	 */
	Topic save(Topic topic);

	/**
	 * update
	 * 
	 * @param topic
	 * @return Topic
	 */
	Topic update(Topic category);

	/**
	 * findByCategoryId
	 * 
	 * @param categoryId
	 * @return Topic
	 */
	Topic findByTopicId(String topicId);

	/**
	 * findByTopicName
	 * 
	 * @param topicName
	 * @return Topic
	 */
	Topic findByTopicName(String topicName);

	/**
	 * delete
	 * 
	 * @param topicId
	 */
	void delete(String topicId);

	/**
	 * getAllTopics
	 * 
	 * @return List<Topic>
	 */
	List<Topic> getAllTopics();

}
