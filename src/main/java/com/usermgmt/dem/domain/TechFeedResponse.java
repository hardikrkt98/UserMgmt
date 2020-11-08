package com.usermgmt.dem.domain;

import java.util.List;

public class TechFeedResponse {

    List <Feed>TechFeeds;

    public TechFeedResponse(List<Feed> techfeeds) {
        this.TechFeeds = techfeeds;

    }

    public List<Feed> getTechFeeds() {
        return TechFeeds;
    }

    public void setTechFeeds(List<Feed> techFeeds) {
        TechFeeds = techFeeds;
    }
}
