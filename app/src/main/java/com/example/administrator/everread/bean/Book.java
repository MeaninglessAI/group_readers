package com.example.administrator.everread.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/16.
 */
public class Book {

    private int book_face;
    private String name_book;
    private int progressBar;
    private int plan_tree;
    private ArrayList<Comment>  comments;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }




    public Book(int book_face, String name_book, int progressBar, int plan_tree) {
        this.book_face = book_face;
        this.name_book = name_book;
        this.progressBar = progressBar;
        this.plan_tree = plan_tree;
    }

    public int getBook_face() {
        return book_face;
    }

    public void setBook_face(int book_face) {
        this.book_face = book_face;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public int getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    public int getPlan_tree() {
        return plan_tree;
    }

    public void setPlan_tree(int plan_tree) {
        this.plan_tree = plan_tree;
    }
}
