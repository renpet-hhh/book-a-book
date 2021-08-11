package model;

import java.util.HashSet;
import java.util.Set;

import controller.RefreshID;
import controller.commands.RefreshCmd;
import framework.App;

public class Author {
    private Set<Book> books = new HashSet<Book>();

    public void addBook(Book book) {
        this.books.add(book);
        App.get().control().invoke(new RefreshCmd(RefreshID.AuthorAddBook, book));
    }
    public Set<Book> getBooks() { return this.books; }


}
