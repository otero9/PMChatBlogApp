package com.udc.muei.tfm.profiledataservice.model.comment;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Interface RateCommentRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface CommentRateRepository extends MongoRepository<CommentRate, String> {

	List<CommentRate> findByUser(User user);

	List<CommentRate> findByComment(Comment comment);

	CommentRate findByUserAndComment(User user, Comment comment);
	
}
