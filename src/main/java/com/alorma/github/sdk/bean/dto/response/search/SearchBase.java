package com.alorma.github.sdk.bean.dto.response.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Bernat on 08/08/2014.
 */
public class SearchBase implements Serializable {

  @SerializedName("total_count") public int totalCount;
  @SerializedName("incomplete_results") public boolean incompleteResults;
}
