package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bernat on 22/08/2014.
 */
public class Milestone extends ShaUrl implements Comparable<Milestone>, Parcelable {

    public static final Creator<Milestone> CREATOR = new Creator<Milestone>() {
        public Milestone createFromParcel(Parcel source) {
            return new Milestone(source);
        }

        public Milestone[] newArray(int size) {
            return new Milestone[size];
        }
    };
    public String title;
    public int number;
    public MilestoneState state;
    public String description;
    public User creator;
    @SerializedName("open_issues")
    public int openIssues;
    @SerializedName("closes_issues")
    public int closedIssues;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("due_on")
    public String dueOn;

    public Milestone() {
    }

    protected Milestone(Parcel in) {
        super(in);
        this.title = in.readString();
        this.number = in.readInt();
        int tmpState = in.readInt();
        this.state = tmpState == -1 ? null : MilestoneState.values()[tmpState];
        this.description = in.readString();
        this.creator = in.readParcelable(User.class.getClassLoader());
        this.openIssues = in.readInt();
        this.closedIssues = in.readInt();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.dueOn = in.readString();
    }

    @Override
    public int compareTo(Milestone another) {
        return title.toLowerCase().compareTo(another.title.toLowerCase());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.title);
        dest.writeInt(this.number);
        dest.writeInt(this.state == null ? -1 : this.state.ordinal());
        dest.writeString(this.description);
        dest.writeParcelable(this.creator, 0);
        dest.writeInt(this.openIssues);
        dest.writeInt(this.closedIssues);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeString(this.dueOn);
    }
}
