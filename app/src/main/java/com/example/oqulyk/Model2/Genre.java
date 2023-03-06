package com.example.oqulyk.Model2;

public class Genre {
    private long gid;
    private String genre;
    private String author;

    public Genre(long gid, String genre, String author) {
        this.gid = gid;
        this.genre = genre;
        this.author = author;
    }

    public Genre(String genre, String author) {
        this.genre = genre;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }
}
