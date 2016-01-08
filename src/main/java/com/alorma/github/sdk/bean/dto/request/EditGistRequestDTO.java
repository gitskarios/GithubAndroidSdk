package com.alorma.github.sdk.bean.dto.request;

import com.alorma.github.sdk.bean.dto.response.GistFile;

import java.io.Serializable;
import java.util.Map;

public class EditGistRequestDTO implements Serializable {

  public String description;
  public Map<String, GistFile> files;
}
