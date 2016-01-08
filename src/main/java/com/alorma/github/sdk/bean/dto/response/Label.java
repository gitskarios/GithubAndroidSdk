package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 22/08/2014.
 */
public class Label extends ShaUrl implements Comparable<Label>, Serializable{

  public String name;
  public String color;

  @Override
  public int compareTo(Label another) {
    return name.toLowerCase().compareTo(another.name.toLowerCase());
  }
}
