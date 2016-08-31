package com.hyperswift.android.recycleit.models;

/**
 * Created by jome on 2016/08/30.
 */
public class User {
    private String name;
    private String profileurl;
    private boolean online;
    private String type;

    public User(String name, String profileurl, boolean online) {
        this.name = name;
        this.profileurl = profileurl;
        this.online = online;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", profileurl='" + profileurl + '\'' +
                ", online=" + online +
                ", type='" + type + '\'' +
                '}';
    }
}
