package com.example.administrator.everread.bean;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lenvov on 2017/5/14.
 */

public class Net_Book implements Serializable{

    private Bitmap imgface;
    private String BookName;
    private String authorName;
    private String bookInfoURL;

    private String press;
    private String presstime;
    private String pages;
    private String ISBN;
    private String coverImgUrl;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    private ArrayList<Comment> comments;
    public String getBook_menu() {
        return book_menu;
    }

    public void setBook_menu(String book_menu) {
        this.book_menu = book_menu;
    }

    private String book_menu;

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    private String author_intro;

    public String getBook_intro() {
        return book_intro;
    }

    public void setBook_intro(String book_intro) {
        this.book_intro = book_intro;
    }

    private String book_intro;

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPresstime() {
        return presstime;
    }

    public void setPresstime(String presstime) {
        this.presstime = presstime;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }



    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }




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
