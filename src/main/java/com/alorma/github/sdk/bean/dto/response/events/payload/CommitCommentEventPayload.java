package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.CommitComment;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 05/10/2014.
 */
public class CommitCommentEventPayload extends ActionEventPayload {
	public CommitComment comment;
	public Repo repository;
	public User sender;
}
