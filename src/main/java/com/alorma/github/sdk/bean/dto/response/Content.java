package com.alorma.github.sdk.bean.dto.response;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class Content extends ShaUrl implements Comparable<Content> {
	public ContentType type;
	public int size;
	public String name;
	public String content;
	public String path;
	public String git_url;
	public Links _links;
	public String encoding;
	public List<Content> children;
	public Content parent;

	public boolean isDir() {
		return ContentType.dir.equals(type);
	}

	public boolean isFile() {
		return ContentType.file.equals(type);
	}

	public boolean isSubmodule() {
		return ContentType.submodule.equals(type);
	}


	@Override
	public int compareTo(Content another) {
		return Comparators.TYPE.compare(this, another);
	}


	public static class Comparators {
		public static Comparator<Content> TYPE = new Comparator<Content>() {
			@Override
			public int compare(Content content, Content content2) {
				if (content.type == ContentType.dir) {
					if (content2.type == ContentType.dir) {
						return content.name.compareTo(content2.name);
					} else {
						return -1;
					}
				} else if (content.type == ContentType.submodule) {
					if (content2.type == ContentType.submodule) {
						return 1;
					} else {
						return -1;
					}
				}
				return content.name.compareTo(content2.name);
			}
		};
	}
}
