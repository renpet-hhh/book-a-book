package model;

import java.util.HashSet;
import java.util.Set;

public class Author {
    private Set<Book> books = new HashSet<Book>();

    public void addBook(Book book) { this.books.add(book); }
    public Set<Book> getBooks() { return this.books; }


}
