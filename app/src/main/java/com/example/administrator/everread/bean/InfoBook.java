package com.example.administrator.everread.bean;

/**
 * Created by Administrator on 2017/4/17.
 */
public class InfoBook {
    //private int face_book;

    private String introduction;

    private String intro_writer;

    private String menu;

    public InfoBook(String introduction, String intro_writer, String menu) {
        this.introduction = introduction;
        this.intro_writer = intro_writer;
        this.menu = menu;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntro_writer() {
        return intro_writer;
    }

    public void setIntro_writer(String intro_writer) {
        this.intro_writer = intro_writer;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
