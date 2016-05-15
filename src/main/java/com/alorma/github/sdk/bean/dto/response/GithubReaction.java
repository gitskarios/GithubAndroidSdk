package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

public class GithubReaction {
    private int id;

    @SerializedName("user_id")
    private int userId;

    private String content;

    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GithubReaction() {
    }

    public GithubReaction(String content, int value) {
        this.content = content;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
