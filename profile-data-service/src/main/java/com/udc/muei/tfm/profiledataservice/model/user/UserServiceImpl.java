package com.udc.muei.tfm.profiledataservice.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udc.muei.tfm.profiledataservice.controller.user.UserDTO;
import com.udc.muei.tfm.profiledataservice.model.blog.Blog;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogRepository;
import com.udc.muei.tfm.profiledataservice.model.comment.Comment;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRepository;
import com.udc.muei.tfm.profiledataservice.model.template.FileRepository;
import com.udc.muei.tfm.profiledataservice.model.template.Template;
import com.udc.muei.tfm.profiledataservice.model.template.TemplateRepository;
import com.udc.muei.tfm.profiledataservice.model.video.Video;
import com.udc.muei.tfm.profiledataservice.model.video.VideoRepository;
import com.udc.muei.tfm.profiledataservice.utils.UserPointsSorter;

/*
 * 
 * The Class UserServiceImpl.
 * 
 * @author a.oteroc
 * 
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	UserRateRepository userRateRepository;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	VideoRepository videoRepository;

	@Autowired
	TemplateRepository templateRepository;

	@Autowired
	FileRepository fileRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsDTO.build(user);
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO user) {

		User userDAO = new User();
		userDAO.setUserName(user.getUserName());
		userDAO.setPassword(user.getPassword());
		userDAO.setFirstName(user.getFirstName());
		userDAO.setLastName(user.getLastName());
		userDAO.setEmail(user.getEmail());
		userDAO.setLocation(user.getLocation());
		userDAO.setBiography(user.getBiography());
		userDAO.setWebSite(user.getWebSite());
		userDAO.setPmiProfile(user.getPmiProfile());
		userDAO.setMarketingEmails(user.isMarketingEmails());
		userDAO.setNotificationEmails(user.isNotificationEmails());
		if (user.getUserName() != null && !user.getUserName().isEmpty()
				&& user.getUserName().equalsIgnoreCase("admin")) {
			userDAO.setAdmin(true);
		} else {
			userDAO.setAdmin(false);
		}
		userDAO.setPoints(0);
		Date actualDate = new Date();
		userDAO.setCreateDate(actualDate);
		userDAO.setUpdateDate(actualDate);
		User _User = userRepository.save(userDAO);
		if (_User != null && _User.getUserId() != null) {
			user.setUserId(_User.getUserId());
			return user;
		}
		return null;
	}

	@Override
	@Transactional
	public UserDTO update(UserDTO user) {
		Optional<User> userData = userRepository.findById(user.getUserId());
		if (userData != null && userData.isPresent() && userData.get() != null) {
			User userDAO = userData.get();
			userDAO.setUserName(user.getUserName());
			if (user.getNewPassword() != null && !user.getNewPassword().isEmpty()) {
				userDAO.setPassword(user.getNewPassword());
			} else {
				userDAO.setPassword(user.getPassword());
			}
			userDAO.setFirstName(user.getFirstName());
			userDAO.setLastName(user.getLastName());
			userDAO.setEmail(user.getEmail());
			userDAO.setLocation(user.getLocation());
			userDAO.setBiography(user.getBiography());
			userDAO.setWebSite(user.getWebSite());
			userDAO.setPmiProfile(user.getPmiProfile());
			userDAO.setMarketingEmails(user.isMarketingEmails());
			userDAO.setNotificationEmails(user.isNotificationEmails());
			Date actualDate = new Date();
			userDAO.setUpdateDate(actualDate);
			User _User = userRepository.save(userDAO);
			if (_User != null && _User.getUserId() != null) {
				return user;
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findByUserId(String userId) {
		Optional<User> userData = userRepository.findById(userId);
		if (userData.isPresent() && userData.get() != null) {
			User user = userData.get();
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.getUserId());
			userDTO.setBirthdate(user.getBirthdate());
			userDTO.setCreateDate(user.getCreateDate());
			userDTO.setUserName(user.getUserName());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setEmail(user.getEmail());
			userDTO.setPoints(user.getPoints());
			List<Blog> blogs = blogRepository.findByUser(userData.get());
			if (blogs != null) {
				userDTO.setCountBlogs(blogs.size());
			} else {
				userDTO.setCountBlogs(0);
			}
			List<Comment> comments = commentRepository.findByUser(userData.get());
			if (comments != null) {
				userDTO.setCountComments(comments.size());
			} else {
				userDTO.setCountComments(0);
			}
			List<Video> videos = videoRepository.findByUser(user);
			if (videos != null && !videos.isEmpty()) {
				userDTO.setCountVideos(videos.size());
			}

			List<Template> templates = templateRepository.findByUser(user);
			if (templates != null && !templates.isEmpty()) {
				userDTO.setCountTemplates(templates.size());
			}
			return userDTO;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findByUserName(String userName) {
		Optional<User> userData = userRepository.findByUserName(userName);
		if (userData.isPresent()) {
			User user = userData.get();
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.getUserId());
			userDTO.setBirthdate(user.getBirthdate());
			userDTO.setCreateDate(user.getCreateDate());
			userDTO.setUserName(user.getUserName());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setEmail(user.getEmail());
			userDTO.setPoints(user.getPoints());
			List<Blog> blogs = blogRepository.findByUser(userData.get());
			if (blogs != null) {
				userDTO.setCountBlogs(blogs.size());
			} else {
				userDTO.setCountBlogs(0);
			}
			List<Comment> comments = commentRepository.findByUser(userData.get());
			if (comments != null) {
				userDTO.setCountComments(comments.size());
			} else {
				userDTO.setCountComments(0);
			}
			List<Video> videos = videoRepository.findByUser(user);
			if (videos != null && !videos.isEmpty()) {
				userDTO.setCountVideos(videos.size());
			}

			List<Template> templates = templateRepository.findByUser(user);
			if (templates != null && !templates.isEmpty()) {
				userDTO.setCountTemplates(templates.size());
			}

			return userDTO;
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(String userId) {
		Optional<User> userData = userRepository.findById(userId);
		if (userData != null && userData.isPresent() && userData.get() != null) {
			/*
			 * List<BlogRate> blogRates = blogRateRepository.findByUser(userData.get()); if
			 * (blogRates != null && !blogRates.isEmpty()) { for (BlogRate blogRate :
			 * blogRates) { blogRateRepository.deleteById(blogRate.getBlogRateId()); } }
			 */
			List<Comment> comments = commentRepository.findByUser(userData.get());
			if (comments != null && !comments.isEmpty()) {
				for (Comment comment : comments) {
					commentRepository.deleteById(comment.getCommentId());
				}
			}
			List<Blog> blogs = blogRepository.findByUser(userData.get());
			if (blogs != null && !blogs.isEmpty()) {
				for (Blog blog : blogs) {
					blogRepository.deleteById(blog.getBlogId());
				}
			}
			userRepository.delete(userData.get());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		if (users != null && !users.isEmpty()) {
			List<UserDTO> usersDto = new ArrayList<UserDTO>();
			for (User user : users) {
				UserDTO userDto = new UserDTO();
				userDto.setUserId(user.getUserId());
				userDto.setUserName(user.getUserName());
				userDto.setPoints(user.getPoints());
				List<Comment> comments = commentRepository.findByUser(user);
				if (comments != null && !comments.isEmpty()) {
					userDto.setCountComments(comments.size());
				}
				List<Blog> blogs = blogRepository.findByUser(user);
				if (blogs != null && !blogs.isEmpty()) {
					userDto.setCountBlogs(blogs.size());
				}
				usersDto.add(userDto);
			}
			return usersDto;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getBestUsers(int numberOfUsers) {
		List<User> users = userRepository.findAll();
		if (users != null && !users.isEmpty()) {
			List<UserDTO> usersDto = new ArrayList<UserDTO>();
			for (User user : users) {

				UserDTO userDto = new UserDTO();
				userDto.setUserId(user.getUserId());
				userDto.setUserName(user.getUserName());
				userDto.setPoints(user.getPoints());

				List<Comment> comments = commentRepository.findByUser(user);
				if (comments != null && !comments.isEmpty()) {
					userDto.setCountComments(comments.size());
				}

				List<Blog> blogs = blogRepository.findByUser(user);
				if (blogs != null && !blogs.isEmpty()) {
					userDto.setCountBlogs(blogs.size());
				}

				List<Video> videos = videoRepository.findByUser(user);
				if (videos != null && !videos.isEmpty()) {
					userDto.setCountVideos(videos.size());
				}

				List<Template> templates = templateRepository.findByUser(user);
				if (templates != null && !templates.isEmpty()) {
					userDto.setCountTemplates(templates.size());
				}

				usersDto.add(userDto);
			}
			usersDto.sort(new UserPointsSorter());
			if (usersDto.size() > 0 && usersDto.size() > numberOfUsers) {
				return usersDto.subList(0, 9);
			} else {
				return usersDto;
			}
		}
		return null;
	}

	@Override
	public UserDTO addPoints(String userId, Long points) {
		Optional<User> user = userRepository.findById(userId);
		if (user != null && user.isPresent() && user.get() != null) {
			User userData = user.get();
			long actualPoints = userData.getPoints();
			userData.setPoints(actualPoints + points);
			User updatedUser = userRepository.save(userData);
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(updatedUser.getUserId());
			userDTO.setBirthdate(updatedUser.getBirthdate());
			userDTO.setCreateDate(updatedUser.getCreateDate());
			userDTO.setUserName(updatedUser.getUserName());
			userDTO.setFirstName(updatedUser.getFirstName());
			userDTO.setLastName(updatedUser.getLastName());
			userDTO.setEmail(updatedUser.getEmail());
			userDTO.setPoints(updatedUser.getPoints());
			userDTO.setLocation(updatedUser.getLocation());
			userDTO.setWebSite(updatedUser.getWebSite());
			userDTO.setBiography(updatedUser.getBiography());
			List<Blog> blogs = blogRepository.findByUser(updatedUser);
			if (blogs != null) {
				userDTO.setCountBlogs(blogs.size());
			} else {
				userDTO.setCountBlogs(0);
			}
			List<Comment> comments = commentRepository.findByUser(updatedUser);
			if (comments != null) {
				userDTO.setCountComments(comments.size());
			} else {
				userDTO.setCountComments(0);
			}
			return userDTO;
		}
		return null;
	}

	@Override
	public boolean existsUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public boolean existsEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User saveUserDAO(User user) {
		User _User = userRepository.save(user);
		if (_User != null && _User.getUserId() != null) {
			user.setUserId(_User.getUserId());
			return user;
		}
		return null;
	}

	@Override
	public User findByUserIdDAO(String userId) {
		Optional<User> _User = userRepository.findById(userId);
		if (_User != null && _User.get() != null) {
			return _User.get();
		}
		return null;
	}

	@Override
	public UserRate newUserRate(String userId, String userRatedId, int value) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData.isPresent()) {
			user = userData.get();
		} else {
			return null;
		}
		User userRated = null;
		Optional<User> userRatedData = userRepository.findById(userRatedId);
		if (userRatedData.isPresent()) {
			userRated = userRatedData.get();
		} else {
			return null;
		}
		if (user != null && userRated != null) {
			long actualPoints = user.getPoints();
			user.setPoints(actualPoints + value);
			userRepository.save(user);
			UserRate valorationData = new UserRate();
			valorationData.setUser(user);
			valorationData.setValuedUser(userRated);
			Date actualDate = new Date();
			valorationData.setCreatedDate(actualDate);
			valorationData.setUpdateDate(actualDate);
			valorationData.setValue(value);
			UserRate createdRate = userRateRepository.save(valorationData);
			if (createdRate != null && createdRate.getUserRateId() != null) {
				return createdRate;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public UserRate findUserRateByUserIdAndUserRatedId(String userId, String userRatedId) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData != null && userData.isPresent() && userData.get() != null) {
			user = userData.get();
		} else {
			return null;
		}
		User userRated = null;
		Optional<User> userRatedData = userRepository.findById(userRatedId);
		if (userRatedData != null && userRatedData.isPresent() && userRatedData.get() != null) {
			userRated = userRatedData.get();
		} else {
			return null;
		}
		UserRate userRate = userRateRepository.findByUserAndValuedUser(user, userRated);
		return userRate;
	}

}
