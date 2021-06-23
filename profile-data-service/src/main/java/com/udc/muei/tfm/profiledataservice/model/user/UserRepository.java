package com.udc.muei.tfm.profiledataservice.model.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * 
 * The Interface UserRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * findByUserName
	 * 
	 * @param userName
	 * @return UserDAO
	 */
	Optional<User> findByUserName(String userName);

	/**
	 * findByUserNameAndPassword
	 * 
	 * @param userName
	 * @param password
	 * @return UserDAO
	 */
	Optional<User> findByUserNameAndPassword(String userName, String password);

	/**
	 * existsByUserName
	 * 
	 * @param userName
	 * @return Boolean
	 */
	Boolean existsByUserName(String userName);

	/**
	 * existsByEmail
	 * 
	 * @param email
	 * @return Boolean
	 */
	Boolean existsByEmail(String email);

}