package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.commands.ReserveBookCmd;

public class Library {

    private Map<String, Set<Book>> books;
    private Map<String, Set<Author>> authors;

    public Library() {
        this.books = new HashMap<String, Set<Book>>();
        this.authors = new HashMap<String, Set<Author>>();
    }
    /* Por enquanto, otimizado para encontrar por Título e por Nome de Autor, mas pode ser alterado */
    public Set<Book> findByTitle(String title) {
        return this.books.getOrDefault(title, new HashSet<Book>());
    }
    public Set<Author> findAuthor(String name) {
        return this.authors.getOrDefault(name, new HashSet<Author>());
    }
    public Set<Book> findByAuthor(String name) {
        Set<Author> authors = this.findAuthor(name);
        Set<Book> books = new HashSet<Book>();
        for (Author a : authors) {
            books.addAll(a.getBooks());
        }
        return books;
    }

    public void reserveBook(Book book) {
        Command reserveCmd = new ReserveBookCmd(book);
        App.get().invoke(reserveCmd);
    }
    /** Adiciona um livro à biblioteca e retorna false se o set não foi alterado. */
    public boolean addBook(Book book) {
        String title = book.getTitle();
        Set<Book> set = this.books.get(title);
        if (set == null) {
            set = new HashSet<>();
            this.books.put(title, set);
        }
        return set.add(book);
    }
}
