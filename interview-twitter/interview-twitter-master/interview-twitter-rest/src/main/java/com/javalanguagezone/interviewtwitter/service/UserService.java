package com.javalanguagezone.interviewtwitter.service;

import com.javalanguagezone.interviewtwitter.domain.Tweet;
import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.repository.TweetRepository;
import com.javalanguagezone.interviewtwitter.repository.UserRepository;
import com.javalanguagezone.interviewtwitter.service.dto.DetailDTO;
import com.javalanguagezone.interviewtwitter.service.dto.TweetDTO;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class UserService implements UserDetailsService {

  private UserRepository userRepository;
  private TweetRepository tweetRepository;

  public UserService(UserRepository userRepository, TweetRepository tweetRepository) {
    this.userRepository = userRepository;
    this.tweetRepository = tweetRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUser(username);
    if(user == null)
      throw new UsernameNotFoundException(username);
    return user;
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowing(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowing());
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowers(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowers());
  }

  @Transactional
  public DetailDTO getUserDetails(String username) {
    User user = getUser(username);
    Integer numberOfTweets = this.tweetRepository.findAllByAuthor(user).size();
    Integer numberOfUsersFollowing = getUsersFollowers(username).size();
    Integer numberOfFollowers = getUsersFollowing(username).size();
    return new DetailDTO(numberOfTweets, numberOfFollowers, numberOfUsersFollowing);

  }

  @Transactional
  private Collection<UserDTO> getUsersFollowing(String username) {
    User user = getUser(username);
    return convertUsersToDTOs(user.getFollowing());
  }

  @Transactional
  private Collection<UserDTO> getUsersFollowers(String username) {
    User user = getUser(username);
    return convertUsersToDTOs(user.getFollowers());
  }

  public UserDTO createUser(User user) {
    try {
      User saved = this.userRepository.save(user);
      return new UserDTO(saved);
    }
    catch(Exception ex) {
      throw new InvalidUserException(user);
    }
  }

  private User getUser(String username) {
    return userRepository.findOneByUsername(username);
  }

  private List<UserDTO> convertUsersToDTOs(Set<User> users) {
    return users.stream().map(UserDTO::new).collect(toList());
  }

  public static class InvalidUserException extends RuntimeException {

    private InvalidUserException(User user) {
      super("'" +  user.toString() + "'");
    }
  }
}
