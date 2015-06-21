package com.alorma.github.sdk;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.ShaUrl;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 30/05/2015.
 */
public class Head extends ShaUrl {
    public String ref;

    public Repo repo;

    public String label;

    public User user;
}
