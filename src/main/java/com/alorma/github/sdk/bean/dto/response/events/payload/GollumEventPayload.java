package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.GollumPage;

import java.io.Serializable;
import java.util.List;

public class GollumEventPayload extends GithubEventPayload implements Serializable {

  public List<GollumPage> pages;
}
