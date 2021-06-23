package com.udc.muei.tfm.profiledataservice.model.video;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Interface VideoRateRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface VideoRateRepository extends MongoRepository<VideoRate, String> {

	List<VideoRate> findByUser(User user);

	List<VideoRate> findByVideo(Video video);

	VideoRate findByUserAndVideo(User user, Video video);

}
