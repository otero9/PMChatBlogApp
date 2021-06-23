package com.udc.muei.tfm.profiledataservice.model.topic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 
 * The Class TopicServiceImpl.
 * 
 * @author a.oteroc
 * 
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Override
	@Transactional
	public Topic save(Topic topic) {
		Date actualDate = new Date();
		topic.setCreateDate(actualDate);
		topic.setUpdateDate(actualDate);
		Topic _topic = topicRepository.save(topic);
		if (_topic != null && _topic.getTopicId() != null) {
			return _topic;
		}
		return null;
	}

	@Override
	@Transactional
	public Topic update(Topic topic) {
		Optional<Topic> topicData = topicRepository.findById(topic.getTopicId());
		if (topicData != null && topicData.isPresent() && topicData.get() != null) {
			Topic _topic = topicData.get();
			Date actualDate = new Date();
			_topic.setUpdateDate(actualDate);
			Topic _Topic = topicRepository.save(_topic);
			if (_Topic != null && _Topic.getTopicId() != null) {
				return _Topic;
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Topic findByTopicId(String topicId) {
		Optional<Topic> topicData = topicRepository.findById(topicId);
		if (topicData.isPresent()) {
			return topicData.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Topic findByTopicName(String topicName) {
		Optional<Topic> topicData = topicRepository.findByTopicName(topicName);
		if (topicData.isPresent()) {
			return topicData.get();
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(String topicId) {
		Optional<Topic> topicData = topicRepository.findById(topicId);
		if (topicData != null && topicData.isPresent() && topicData.get() != null) {
			topicRepository.delete(topicData.get());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<Topic>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}

}
