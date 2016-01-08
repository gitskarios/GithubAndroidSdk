package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.User;

import java.io.Serializable;

public class MemberEventPayload extends ActionEventPayload implements Serializable {
  public User member;
}
