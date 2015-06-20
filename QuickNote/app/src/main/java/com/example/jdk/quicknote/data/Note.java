package com.example.jdk.quicknote.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class Note implements Serializable{

    private final String title;
    private final String content;
    private final Date date;
    private int favourite;

    public Note(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
}
