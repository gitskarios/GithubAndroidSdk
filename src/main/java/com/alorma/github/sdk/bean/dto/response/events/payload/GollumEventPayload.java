package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.GollumPage;

import java.util.List;

public class GollumEventPayload extends GithubEventPayload {

  public List<GollumPage> pages;
}
