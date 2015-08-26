package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;

public class GitReferenceEntry extends ShaUrl{
    public String type;

    protected GitReferenceEntry(Parcel in){
        super(in);
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(type);
    }

    public static final Creator<GitReferenceEntry> CREATOR = new Creator<GitReferenceEntry>() {
        @Override
        public GitReferenceEntry createFromParcel(Parcel in) {
            return new GitReferenceEntry(in);
        }

        @Override
        public GitReferenceEntry[] newArray(int size) {
            return new GitReferenceEntry[size];
        }
    };
}
