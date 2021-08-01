package model;

import java.util.List;

public class Book {
    private String title, subtitle, edition, isbn, whereWasPublished;
    private List<String> authors;
    private int yearOfPublishment;
    private int howManyTotal = 0, howManyAvailable = 0;

    public Book(String title, String subtitle, String edition, String isbn, String whereWasPublished, List<String> authors, int yearOfPublishment) {
        this.title = title;
        this.subtitle = subtitle;
        this.edition = edition;
        this.isbn = isbn;
        this.authors = authors;
        this.yearOfPublishment = yearOfPublishment;
    }

    /** Getters */
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getEdition() { return edition; }
    public List<String> getAuthors() { return authors; }
    public String getIsbn() { return isbn; }
    public String getWhereWasPublished() { return whereWasPublished; }
    public int getYearOfPublishment() { return yearOfPublishment; }

    public int getHowManyTotal() { return this.howManyTotal; }
    public void setHowManyTotal(int howMany) { this.howManyTotal = howMany; }
    public int getHowManyAvailable() { return this.howManyAvailable; }
    public void setHowManyAvailable(int howMany) { this.howManyAvailable = howMany; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Book)) return false;
        Book b = (Book)obj;
        return b.isbn.equals(this.isbn);
    }

    @Override
    public int hashCode() {
        return this.isbn.hashCode();
    }
}

