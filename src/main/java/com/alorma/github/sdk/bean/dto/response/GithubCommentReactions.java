package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class GithubCommentReactions extends HashMap<String, Object>{

    private int totalCount;
    private int plusOne;
    private int minusOne;
    private int laugh;
    private int confused;
    private int heart;
    private int hooray;

    private String url;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPlusOne() {
        return plusOne;
    }

    public void setPlusOne(int plusOne) {
        this.plusOne = plusOne;
    }

    public int getMinusOne() {
        return minusOne;
    }

    public void setMinusOne(int minusOne) {
        this.minusOne = minusOne;
    }

    public int getLaugh() {
        return laugh;
    }

    public void setLaugh(int laugh) {
        this.laugh = laugh;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getHooray() {
        return hooray;
    }

    public void setHooray(int hooray) {
        this.hooray = hooray;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getConfused() {
        return confused;
    }

    public void setConfused(int confused) {
        this.confused = confused;
    }
}
