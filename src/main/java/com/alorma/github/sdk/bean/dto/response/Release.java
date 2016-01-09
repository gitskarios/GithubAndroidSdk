package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class Release implements Parcelable {

    public static final Parcelable.Creator<Release> CREATOR = new Parcelable.Creator<Release>() {
        public Release createFromParcel(Parcel source) {
            return new Release(source);
        }

        public Release[] newArray(int size) {
            return new Release[size];
        }
    };
    public String body;
    public String upload_url;
    public String assets_url;
    public String tag_name;
    public String url;
    public String published_at;
    public String html_url;
    public String id;
    public String target_commitish;
    public List<ReleaseAsset> assets;
    public boolean draft;
    public User author;
    public String zipball_url;
    public boolean prerelease;
    public String tarball_url;
    public String name;
    public Date created_at;

    public Release() {

    }

    protected Release(Parcel in) {
        this.body = in.readString();
        this.upload_url = in.readString();
        this.assets_url = in.readString();
        this.tag_name = in.readString();
        this.url = in.readString();
        this.published_at = in.readString();
        this.html_url = in.readString();
        this.id = in.readString();
        this.target_commitish = in.readString();
        this.assets = new ArrayList<ReleaseAsset>();
        in.readList(this.assets, List.class.getClassLoader());
        this.draft = in.readByte() != 0;
        this.author = in.readParcelable(User.class.getClassLoader());
        this.zipball_url = in.readString();
        this.prerelease = in.readByte() != 0;
        this.tarball_url = in.readString();
        this.name = in.readString();
        long tmpCreated_at = in.readLong();
        this.created_at = tmpCreated_at == -1 ? null : new Date(tmpCreated_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
        dest.writeString(this.upload_url);
        dest.writeString(this.assets_url);
        dest.writeString(this.tag_name);
        dest.writeString(this.url);
        dest.writeString(this.published_at);
        dest.writeString(this.html_url);
        dest.writeString(this.id);
        dest.writeString(this.target_commitish);
        dest.writeList(this.assets);
        dest.writeByte(draft ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.author, 0);
        dest.writeString(this.zipball_url);
        dest.writeByte(prerelease ? (byte) 1 : (byte) 0);
        dest.writeString(this.tarball_url);
        dest.writeString(this.name);
        dest.writeLong(created_at != null ? created_at.getTime() : -1);
    }
}
