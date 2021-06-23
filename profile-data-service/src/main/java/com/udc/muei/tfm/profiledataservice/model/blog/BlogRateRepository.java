package com.udc.muei.tfm.profiledataservice.model.blog;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Interface BlogRateRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface BlogRateRepository extends MongoRepository<BlogRate, String> {

	List<BlogRate> findByUser(User user);

	List<BlogRate> findByBlog(Blog blog);

	BlogRate findByUserAndBlog(User user, Blog blog);

}
