package com.example.miertkelleztafeladatotcsinalni;

public class Book {
    public int id;
    public String Title;
    public String Author;
    public int Page_count;
    public int Release_year;

    public Book(int id, String title, String author, int page_count, int release_year) {
        this.id = id;
        Title = title;
        Author = author;
        Page_count = page_count;
        Release_year = release_year;
    }
}
