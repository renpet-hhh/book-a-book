package model;

import java.util.List;

public class Book {
    private String title, subtitle, edition, isbn;
    private List<Author> authors;
    private int yearOfPublishment;
    private int howManyTotal, howManyAvailable;

    public Book(String title, String subtitle, String edition, String isbn, List<Author> authors, int yearOfPublishment) {
        this.title = title;
        this.subtitle = subtitle;
        this.edition = edition;
        this.isbn = isbn;
        this.authors = authors;
        this.yearOfPublishment = yearOfPublishment;
    }

    public int getHowManyTotal() { return this.howManyTotal; }
    public void setHowManyTotal(int howMany) { this.howManyTotal = howMany; }
    public int getHowManyAvailable() { return this.howManyAvailable; }
    public void setHowManyAvailable(int howMany) { this.howManyAvailable = howMany; }
}
