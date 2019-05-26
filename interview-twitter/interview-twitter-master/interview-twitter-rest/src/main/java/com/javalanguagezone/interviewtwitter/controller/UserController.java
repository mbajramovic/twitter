package com.javalanguagezone.interviewtwitter.controller;

import com.javalanguagezone.interviewtwitter.controller.dto.ErrorMessage;
import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.service.UserService;
import com.javalanguagezone.interviewtwitter.service.UserService.InvalidUserException;
import com.javalanguagezone.interviewtwitter.service.dto.DetailDTO;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/followers")
  public Collection<UserDTO> followers(Principal principal) {
    return userService.getUsersFollowers(principal);
  }

  @GetMapping("/following")
  public Collection<UserDTO> following(Principal principal) {
    return userService.getUsersFollowing(principal);
  }

  @GetMapping("/details/{username}")
  public DetailDTO getUserDetauls(@PathVariable String username) {
    return userService.getUserDetails(username);
  }

  @PostMapping("/user")
  public UserDTO createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorMessage handleInvalidTweetException(InvalidUserException e){
    //log.warn("", e);
    return new ErrorMessage("Invalid user");
  }
 }
