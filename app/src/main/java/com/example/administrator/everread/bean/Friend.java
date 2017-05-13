package com.example.administrator.everread.bean;

/**
 * Created by Administrator on 2017/4/9.
 */
public class Friend {
    private String name_friend;

    private String idea_friend;

    private int image;

    public Friend(String name_friend, String idea_friend, int image) {
        this.name_friend = name_friend;
        this.idea_friend = idea_friend;
        this.image = image;
    }

    public String getName_friend() {
        return name_friend;
    }

    public String getIdea_friend() {
        return idea_friend;
    }

    public int getImage() {
        return image;
    }
}
