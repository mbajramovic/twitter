package com.javalanguagezone.interviewtwitter.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class DetailDTO {
    private  Integer numberOfTweets;
    private  Integer numberOfFollowers;
    private  Integer numberOfUsersFollowing;

    public DetailDTO(Integer numberOfTweets, Integer numberOfFollowers, Integer numberOfUsersFollowing) {
        this.numberOfTweets = numberOfTweets;
        this.numberOfFollowers = numberOfFollowers;
        this.numberOfUsersFollowing = numberOfUsersFollowing;
    }

    public Integer getNumberOfTweets() {
        return numberOfTweets;
    }

    public void setNumberOfTweets(Integer numberOfTweets) {
        this.numberOfTweets = numberOfTweets;
    }

    public Integer getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(Integer numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public Integer getNumberOfUsersFollowing() {
        return numberOfUsersFollowing;
    }

    public void setNumberOfUsersFollowing(Integer numberOfUsersFollowing) {
        this.numberOfUsersFollowing = numberOfUsersFollowing;
    }
}