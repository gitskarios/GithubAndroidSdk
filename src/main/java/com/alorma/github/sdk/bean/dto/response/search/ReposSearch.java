package com.alorma.github.sdk.bean.dto.response.search;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bernat on 08/08/2014.
 */
public class ReposSearch extends SearchBase implements Serializable {
  public List<Repo> items;
}
