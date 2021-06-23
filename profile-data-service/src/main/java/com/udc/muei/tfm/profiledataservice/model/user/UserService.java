package com.udc.muei.tfm.profiledataservice.model.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udc.muei.tfm.profiledataservice.controller.user.UserDTO;

/*
 * 
 * The Interface UserRepository.
 * 
 * @author a.oteroc
 * 
 */
@Service
public interface UserService {

	/**
	 * save
	 * 
	 * @param user
	 * @return UserDTO
	 */
	UserDTO save(UserDTO user);

	/**
	 * save
	 * 
	 * @param user
	 * @return User
	 */
	User saveUserDAO(User user);

	/**
	 * update
	 * 
	 * @param user
	 * @return UserDTO
	 */
	UserDTO update(UserDTO user);

	/**
	 * findByUserId
	 * 
	 * @param userId
	 * @return UserDTO
	 */
	UserDTO findByUserId(String userId);

	/**
	 * findByUserIdDAO
	 * 
	 * @param userId
	 * @return User
	 */
	User findByUserIdDAO(String userId);

	/**
	 * findByUserName
	 * 
	 * @param userName
	 * @return UserDTO
	 */
	UserDTO findByUserName(String userName);

	/**
	 * delete
	 * 
	 * @param userId
	 */
	void delete(String userId);

	/**
	 * getAllUsers
	 * 
	 * @return List<UserDAO>
	 */
	List<UserDTO> getAllUsers();

	/**
	 * getBestUsers
	 * 
	 * @param numberOfUsers
	 * @return List<UserDTO>
	 */
	List<UserDTO> getBestUsers(int numberOfUsers);

	/**
	 * addPoints
	 * 
	 * @param userId
	 * @param points
	 * @return UserDTO
	 */
	UserDTO addPoints(String userId, Long points);

	/**
	 * existsUserName
	 * 
	 * @param userName
	 * @return boolean
	 */
	boolean existsUserName(String userName);

	/**
	 * existsEmail
	 * 
	 * @param email
	 * @return boolean
	 */
	boolean existsEmail(String email);

	/**
	 * newUserRate
	 * 
	 * @param userId
	 * @param userRatedId
	 * @param value
	 * @return UserRate
	 */
	UserRate newUserRate(String userId, String userRatedId, int value);

	/**
	 * findUserRateByUserIdAndUserRatedId
	 * 
	 * @param userId
	 * @param userRatedId
	 * @return UserRate
	 */
	UserRate findUserRateByUserIdAndUserRatedId(String userId, String userRatedId);

}