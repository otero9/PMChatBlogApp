package com.udc.muei.tfm.profiledataservice.model.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * 
 * The Interface UserRateRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface UserRateRepository extends MongoRepository<UserRate, String> {

	List<UserRate> findByUser(User user);

	List<UserRate> findByValuedUser(User valuedUser);

	UserRate findByUserAndValuedUser(User user, User valuedUser);

}