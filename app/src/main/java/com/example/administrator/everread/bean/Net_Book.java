package com.example.administrator.everread.bean;

import android.graphics.Bitmap;

/**
 * Created by Lenvov on 2017/5/14.
 */

public class Net_Book {

    private Bitmap imgface;
    private String BookName;
    private String authorName;
    private String bookInfoURL;

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    private String coverImgUrl;


    public Bitmap getImgface() {
        return imgface;
    }

    public void setImgface(Bitmap imgface) {
        this.imgface = imgface;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookInfoURL() {
        return bookInfoURL;
    }

    public void setBookInfoURL(String bookInfoURL) {
        this.bookInfoURL = bookInfoURL;
    }


}
