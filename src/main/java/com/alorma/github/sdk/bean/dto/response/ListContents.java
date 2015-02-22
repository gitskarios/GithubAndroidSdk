package com.alorma.github.sdk.bean.dto.response;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Bernat on 20/07/2014.
 */
public class ListContents extends ArrayList<Content> {

    public ListContents(int capacity) {
        super(capacity);
    }
    public ListContents() {
        super();
    }

}
