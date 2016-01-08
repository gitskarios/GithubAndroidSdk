package com.alorma.github.sdk.bean.dto.response.search;

import com.alorma.github.sdk.bean.dto.response.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bernat on 08/08/2014.
 */
public class UsersSearch extends SearchBase implements Serializable {
  public List<User> items;
}
