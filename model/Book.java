package model;

import java.util.List;

import controller.RefreshID;
import controller.commands.RefreshCmd;
import framework.App;

public class Book {
    private String title, subtitle, edition, isbn, whereWasPublished;
    private List<String> authors;
    private int yearOfPublishment;
    private int howManyTotal = 0, howManyAvailable = 0, howManyReserved = 0;

    /* Método construtor */
    public Book(String title, String subtitle, String edition, String isbn, String whereWasPublished, List<String> authors, int yearOfPublishment, int total) {
        this.title = title;
        this.subtitle = subtitle;
        this.edition = edition;
        this.isbn = isbn;
        this.whereWasPublished = whereWasPublished;
        this.authors = authors;
        this.yearOfPublishment = yearOfPublishment;
        this.setHowManyTotal(total);
        this.setHowManyAvailable(total);
        this.setHowManyReserved(0);
    }

    /** Getters */
    public String getTitle() { return this.title; }
    public String getSubtitle() { return this.subtitle; }
    public String getEdition() { return this.edition; }
    public List<String> getAuthors() { return this.authors; }
    public String getIsbn() { return this.isbn; }
    public String getWhereWasPublished() { return this.whereWasPublished; }
    public int getYearOfPublishment() { return this.yearOfPublishment; }

    public int getHowManyTotal() { return this.howManyTotal; }
    public void setHowManyTotal(int howMany) {
        this.howManyTotal = howMany;
        App.get().control().invoke(new RefreshCmd(RefreshID.BookTotal)); // notificamos as views observadoras
    }
    public int getHowManyAvailable() { return this.howManyAvailable; }
    public void setHowManyAvailable(int howMany) {
        this.howManyAvailable = howMany;
        App.get().control().invoke(new RefreshCmd(RefreshID.BookAvailable)); // notificamos as views observadoras
    }
    public int getHowManyReserved() { return this.howManyReserved; }
    public void setHowManyReserved(int howMany) {
        this.howManyReserved = howMany;
        App.get().control().invoke(new RefreshCmd(RefreshID.BookReserved)); // notificamos as views observadoras
    }
    public int getHowManyRented() {
        return this.getHowManyTotal() - this.getHowManyAvailable() - this.getHowManyReserved();
    }

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

    public Book copy() {
        Book b = new Book(title, subtitle, edition, isbn, whereWasPublished, authors, yearOfPublishment, this.getHowManyTotal());
        b.setHowManyAvailable(this.getHowManyAvailable());
        b.setHowManyReserved(this.getHowManyReserved());
        return b;
    }
}

