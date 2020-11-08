package com.usermgmt.dem.domain;

public class Feed {
    String feed;
    String url;

    public String getFeed() {
        return feed;
    }

    public Feed(String feed, String url) {
        this.feed = feed;
        this.url = url;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
